package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.NotExistingPlayerException;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Bag;
import it.polimi.ingsw.model.gameboard.CharacterDeck;
import it.polimi.ingsw.model.gameboard.Characters.CharacterCard;
import it.polimi.ingsw.model.gameboard.Cloud;
import it.polimi.ingsw.model.gameboard.GameBoard;

import java.util.ArrayList;
import java.util.Collections;

/*
 *  In this class we manage the main actions of the match.
 */

public class Game {
    private final ArrayList<Player> players;
    private final GameBoard gameBoard;
    private CharacterDeck characterDeck;
    private ArrayList<CharacterCard> threeCharacterCards;
    private int currentPlayer;
    private GamePhase currentPhase;
    private Player[] playersTurnOrder;

    private int phaseCounter;
    private int playerPhaseCounter;


    //Constructor Game creates a new Game instance
    public Game() {
        players = new ArrayList<>();
        gameBoard = new GameBoard();
        characterDeck = new CharacterDeck();
        phaseCounter = 0;
        playerPhaseCounter = 0;
    }

    //Gets the players of the match
    public ArrayList<Player> getPlayers() {
        return players;
    }


    //Gets the gameBoard instance
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameParameters(int num,boolean mode){
        Parameters.setParameters(num,mode);
    }

    public void addPlayer(String nickname){   //DA RIFAREEEEEE

        if(players.size() < Parameters.numPlayers) {
            Player p = new Player(nickname, players.size());
            players.add(p);
        }
        else{
            //qui bisogna mettere una exception che nella partita ci sono già 4 giocatori
        }
    }

    public void init(){   //sto seguendo l'inizializzazione della partita
        if(Parameters.expertMode){
            threeCharacterCards = characterDeck.getThreeRandomCards();
        }

        double casual = Math.random()*12; //(PUNTO 2)
        int mn = (int) casual;

        gameBoard.setMotherNature(mn);  //posiziono madrenatura

        ArrayList<Student> arr = new ArrayList<>();  //(PUNTO 3)creo studenti per le isole
        for (Colour c : Colour.values()) {
            for(int i=0;i<2;i++){
                Student s = new Student(c);
                arr.add(s);
            }
        }
        gameBoard.getBag().fill(arr);

        for (int i=0; i<12; i++){
            if(i != gameBoard.getMotherNature() && i != (gameBoard.getMotherNature() + 6)%12) //doesn't place students on the island with mothernature and the opposite one
                gameBoard.addStudentOnIsland(i, gameBoard.getBag().draw());
        }

        for (Colour c : Colour.values()) {  //creo gli studenti per il sacchetto
            for(int i=0;i<24;i++){
                Student s = new Student(c);
                arr.add(s);
            }
        }
        gameBoard.getBag().fill(arr);

        for (Player p: players){
            for (int i=0; i<Parameters.entranceStudents; i++)
                p.getPlayerSchoolBoard().getStudentsEntrance().add(gameBoard.getBag().draw());
        }
    }


    //Fase pianificazione punto 1
    public void addStudentsOnClouds(){
        for(Cloud c: gameBoard.getClouds())
            for(int i=0; i<Parameters.numCloudStudents; i++)
                gameBoard.addStudentOnCloud(i, gameBoard.getBag().draw());
    }
    //Fase pianificazione punto 2
    public void playAssistantCard(int playerIndex, int priority){
        if(currentPhase.equals(GamePhase.PlayAssistantCard) && playerIndex == currentPlayer) {

            int check = 0;
            for(AssistantCard as: players.get(playerIndex).getAssistantDeck()){
                for(int i=0; i<phaseCounter; i++){
                    if(as.getValue() == playersTurnOrder[phaseCounter].getCurrentCard().getValue()){
                        check++;
                    }
                }
            }

            if (check != players.get(playerIndex).getAssistantDeck().size())
                return;

            for (AssistantCard as : players.get(playerIndex).getAssistantDeck())
                if (as.getValue() == priority)
                    players.get(playerIndex).playCard(as);

            currentPlayer = playersTurnOrder[phaseCounter + 1].getIndex();
            phaseCounter++;
            if (phaseCounter == Parameters.numPlayers) {
                currentPhase = GamePhase.MoveStudents;
                phaseCounter = 0;
                this.orderPlayerActionPhase();
                currentPlayer = playersTurnOrder[0].getIndex();
            }
        }
    }

    private void orderPlayerActionPhase(){
        for(int i = 0; i < playersTurnOrder.length; i++){
            boolean swap = false;
            for(int j = 0; j < playersTurnOrder.length-1; j++){
                if(playersTurnOrder[j].getCurrentCard().getValue() > playersTurnOrder[j+1].getCurrentCard().getValue()) {
                    Player k = playersTurnOrder[j];
                    playersTurnOrder[j] = playersTurnOrder[j+1];
                    playersTurnOrder[j+1] = k;
                    swap = true; //segna che è avvenuto uno scambio
                }
            }
            if(!swap) break; // esce prima se ha già ordinato tutto
        }
    }


    public void playCharacterCard(int playerIndex, int cardIndex){
        if(playerIndex == currentPlayer /*&& players.get(playerIndex).getCoins() >= cardIndex.costo*/){
            //cardindex.play() o comunque si attiva il suo effetto, inoltre il costo viene implementato di uno
            //gameBoard.addCoinsToGeneralReserve(cardIndex - 1); //meno uno perchè una va sulla carta
        }
    }

    //Fase azione punto 1
    public void moveStudentToIsland(int playerIndex, int colour, int IslandPosition){
        if(currentPhase.equals(GamePhase.MoveStudents) && playerIndex == currentPlayer){
            players.get(playerIndex).getPlayerSchoolBoard().moveStudentToIsland(colour, this.gameBoard.getIslands().get(IslandPosition));

            phaseCounter++;
            if(phaseCounter == Parameters.numCloudStudents){
                currentPhase = GamePhase.MoveMotherNature;
                phaseCounter = 0;
            }
        }
    }
    //Fase azione punto 1
    public void moveStudentToDiningRoom(int playerIndex, int colour){
        if(currentPhase.equals(GamePhase.MoveStudents) && playerIndex == currentPlayer){
            boolean coin; //ritorna true se il giocatore merita una moneta
            coin = players.get(playerIndex).getPlayerSchoolBoard().moveStudentToDiningRoom(colour);
            if(Parameters.expertMode && coin){
                players.get(playerIndex).addCoin();
                gameBoard.getOneCoin();
            }

            //controllo chi possiede il professore ora
            int c;
            int max = 0;
            Player oldProfessorOwner = null;
            Player newProfessorOwner = null;
            for(Player p: players){
                //vedo chi aveva il professore prima
                for(Professor pr: p.getPlayerSchoolBoard().getProfessors()){
                    if(pr.getProfessorColour().equals(Colour.values()[colour]))
                        oldProfessorOwner = p;
                }
                //vedo chi merita il professore ora
                c = p.getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colour]);
                if(c > max){
                    max = c;
                    newProfessorOwner = p;
                }
            }
            //se il propretario è cambiato faccio il passaggio
            if(!oldProfessorOwner.equals(newProfessorOwner)){
                oldProfessorOwner.getPlayerSchoolBoard().removeProfessor(Colour.values()[colour]);
                newProfessorOwner.getPlayerSchoolBoard().addProfessor(Colour.values()[colour]);
            }

            phaseCounter++;
            if(phaseCounter == Parameters.numCloudStudents){
                currentPhase = GamePhase.MoveMotherNature;
                phaseCounter = 0;
            }
        }
    }

    //Fase azione punto 2.1
    public void moveMotherNature(int playerIndex, int movements){ //devo controllare se player ha i permessi
        if(currentPhase.equals(GamePhase.MoveMotherNature) && playerIndex == currentPlayer){
            if(movements > 0 && movements <= players.get(currentPlayer).getCurrentCard().getMovements()){
                int newPos = ((this.gameBoard.getMotherNature() + movements)%Parameters.numIsland);
                this.gameBoard.setMotherNature(newPos);//moves motherNature by specified movements
                this.checkIslandInfluence(newPos);
            }

            currentPhase = GamePhase.ChooseCloud;
        }
    }
    //Fase azione punto 2.2 //vedo chi controlla l'isola, se il player è cambiato sostituisco le torri
    private void checkIslandInfluence(int islandIndex){

        // vedo chi ha più influenza ora
        Player newPlayer = this.gameBoard.getIslands().get(islandIndex).Influence(this.players);

        if (newPlayer!=null){
            Player oldPlayer = null;  // vedo chi controllava l'isola prima
            for (Player pl: players){
                if(pl.PlayerTowerColor().equals(this.gameBoard.getIslands().get(islandIndex).getTowerColor()))
                    oldPlayer = pl;
            }

            //ridò al vecchio giocatore le sue torri e le sostituisco con quelle del nuovo giocatore
            for(int i=0; i<=this.gameBoard.getIslands().get(islandIndex).getNumTower(); i++){
                if(oldPlayer != null)
                    oldPlayer.getPlayerSchoolBoard().addTower(oldPlayer.PlayerTowerColor());
                newPlayer.getPlayerSchoolBoard().getTowers().remove(0);
            }
            this.getGameBoard().getIslands().get(islandIndex).changeTowerColor(newPlayer.PlayerTowerColor());

            checkIslandFusion(islandIndex);

        } else return; //se non c'è ancora una torre sull'isola o in caso di parità non succede nulla
    }
    //Fase azione punto 2.3
    private void checkIslandFusion(int islandIndex){
        //se il colore delle torri sull'isola e su quella successiva sono uguali
        while (this.gameBoard.getIslands().get(islandIndex).getTowerColor().equals(this.gameBoard.getIslands().get(islandIndex+1).getTowerColor())){
            this.gameBoard.islandFusion(islandIndex, islandIndex+1);
        }
        int newPosition = islandIndex;
        //se il colore delle torri sull'isola e su quella precedente sono uguali
        while (this.gameBoard.getIslands().get(newPosition).getTowerColor().equals(this.gameBoard.getIslands().get(newPosition-1).getTowerColor())){
            this.gameBoard.islandFusion(newPosition-1, newPosition);
            newPosition--;
        }
    }

    //Fase azione punto 3
    public void chooseCloud(int playerIndex, int cloudPosition){
        if(currentPhase.equals(GamePhase.MoveMotherNature) && playerIndex == currentPlayer){
            ArrayList<Student> stud = this.gameBoard.getClouds().get(cloudPosition).getStudents();

            for (Student s: stud){ //qui e alla fine del metodo init() farei un nuovo metodo add che controlla anche se non viene superato il limite di studenti all'entrata
                players.get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(s);
            }

            playerPhaseCounter++;
            if(playerPhaseCounter == Parameters.numPlayers){
                playerPhaseCounter = 0;
                addStudentsOnClouds();
                orderPlayersAssistantCard();
                currentPhase = GamePhase.PlayAssistantCard;
            } else {
                currentPhase = GamePhase.MoveStudents;
                currentPlayer = playersTurnOrder[playerPhaseCounter].getIndex();
            }
        }
    }

    private void orderPlayersAssistantCard(){
        int index = -1;
        int num;
        int min = 11;
        for(Player p: players){
            num = p.getCurrentCard().getValue();
            if(num < min){
                min = num;
                index = p.getIndex();
            }
        }

        currentPlayer = index;
        for(int i = 0; i<Parameters.numPlayers; i++)
            playersTurnOrder[i] = players.get((index + i)%Parameters.numPlayers);
    }

    public int getCurrentPlayer(){
        return players.indexOf(currentPlayer);
    }
}