package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.NotExistingPlayerException;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Bag;
import it.polimi.ingsw.model.gameboard.CharacterDeck;
import it.polimi.ingsw.model.gameboard.Characters.*;
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
    private int currentPlayer;
    private GamePhase currentPhase;
    private Player[] playersTurnOrder;

    private int phaseCounter;
    private int playerPhaseCounter;


    //Constructor Game creates a new Game instance
    public Game() {
        players = new ArrayList<>();
        gameBoard = new GameBoard();

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

    public int addPlayer(String nickname){   //DA RIFAREEEEEE

        if(players.size() <= Parameters.numPlayers) {
            Player p = new Player(nickname, players.size());
            players.add(p);
            return players.size()-1;
        }
        else{
            //qui bisogna mettere una exception che nella partita ci sono già 4 giocatori
            return -1;
        }
    }

    public void init(){   //sto seguendo l'inizializzazione della partita
        if(Parameters.expertMode){
            gameBoard.chooseThreeCards();
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
        int num = 0;
        for(Cloud c: gameBoard.getClouds()){
            for (int i = 0; i < Parameters.numCloudStudents; i++)
                if(gameBoard.getBag().getSize() > 0)
                    gameBoard.addStudentOnCloud(num, gameBoard.getBag().draw());
            num++;
        }
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
                if (as.getValue() == priority){
                    players.get(playerIndex).playAssistantCard(as);
                } else return; //non ha la carta, non ha senso proseguire, tocca ancora lui

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
        if(currentPhase.equals(GamePhase.MoveStudents) && playerIndex == currentPlayer && players.get(playerIndex).getPlayerSchoolBoard().getDiningRoom().numOfStudentByColor(Colour.values()[colour]) < 10){
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

                int newPos = 0;
                if(Parameters.expertMode){
                    for(CharacterCard c: gameBoard.getThreeCharacterCards())
                        if(c.getIndex() == 4 && ((Character4) c).isEffectFlag())
                            newPos = 2;
                }
                newPos = ((newPos + this.gameBoard.getMotherNature() + movements)%Parameters.numIsland);
                this.gameBoard.setMotherNature(newPos);//moves motherNature by specified movements
                this.checkIslandInfluence(newPos, playerIndex);
            }

            currentPhase = GamePhase.ChooseCloud;
        }
    }
    //Fase azione punto 2.2 //vedo chi controlla l'isola, se il player è cambiato sostituisco le torri
    private void checkIslandInfluence(int islandIndex, int playerIndex){
        boolean noTowerFlag = false;
        int twoMorePointsPlayer = -1;
        if(Parameters.expertMode){
            for(CharacterCard c: gameBoard.getThreeCharacterCards())
                if(c.getIndex() == 5 && gameBoard.getIslands().get(islandIndex).getNoEntry() > 0){
                    gameBoard.getIslands().get(islandIndex).removeNoEntry();
                    ((Character5) c).addNoEntry();
                    return;  // proseguo come se madre natura non fosse capitata qui
                } else if(c.getIndex() == 6 && ((Character6) c).isEffectFlag()){
                    ((Character6) c).disableEffect();
                    noTowerFlag = true;
                } else if(c.getIndex() == 8 && ((Character8) c).isEffectFlag()){
                    ((Character6) c).disableEffect();
                    twoMorePointsPlayer = playerIndex;
                }

        }

        // vedo chi ha più influenza ora
        Player newPlayer = this.gameBoard.getIslands().get(islandIndex).Influence(this.players, noTowerFlag, twoMorePointsPlayer);

        if (newPlayer!=null){
            Player oldPlayer = null;  // vedo chi controllava l'isola prima
            for (Player pl: players){
                if(pl.PlayerTowerColor().equals(this.gameBoard.getIslands().get(islandIndex).getTowerColor()))
                    oldPlayer = pl;
            }

            if(!oldPlayer.equals(newPlayer)){
                //ridò al vecchio giocatore le sue torri e le sostituisco con quelle del nuovo giocatore
                for(int i=0; i<=this.gameBoard.getIslands().get(islandIndex).getNumTower(); i++){
                    if(oldPlayer != null)
                        oldPlayer.getPlayerSchoolBoard().addTower(oldPlayer.PlayerTowerColor());
                    newPlayer.getPlayerSchoolBoard().getTowers().remove(0);
                }
                this.getGameBoard().getIslands().get(islandIndex).changeTowerColor(newPlayer.PlayerTowerColor());

                if(newPlayer.getPlayerSchoolBoard().getTowers().size() == 0){
                    endGame();
                }

                checkIslandFusion(islandIndex);
            }

        }
        //se nessuno ha l'influenza o in caso di parità non succede nulla
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
        if(this.gameBoard.getIslands().size() <= 3){
            endGame();
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

                if(gameBoard.getBag().getSize() == 0)  //bisogna mettere delle exception dove viene pescato uno studente se non ce ne sono più, poi qui finisco il gioco.
                    endGame();

                for(Player p: players)
                    if(p.getAssistantDeck().size() == 0)
                        endGame();

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

    public void endGame(){
        //non so cosa succeda qui dentro
        //vince il giocatore che ha costruito il maggior numero di torri
        //in caso di parità chi ha piu professori
        currentPhase = GamePhase.GameEnded;
    }

    public int getCurrentPhase() {
        return currentPhase.ordinal();
    }

    public int getCurrentPlayer(){
        return players.indexOf(currentPlayer);
    }











    public void playCharacterCard1(int playerIndex, int cardIndex, int colorIndex, int islandIndex){
        if(playerIndex == currentPlayer){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost() && ((Character1) c).checkColorExists(colorIndex)){
                    c.play();
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta

                    gameBoard.addStudentOnIsland(islandIndex, ((Character1) c).getStudent(colorIndex));
                    ((Character1) c).addStudent(gameBoard.getBag().draw());
                }
            }
        }
    }

    public void playCharacterCard3(int playerIndex, int cardIndex, int islandIndex){
        if(playerIndex == currentPlayer){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    c.play();
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta

                    checkIslandInfluence(islandIndex, playerIndex);
                }
            }
        }
    }

    public void playCharacterCard4(int playerIndex, int cardIndex){
        if(playerIndex == currentPlayer && currentPhase.ordinal() <= GamePhase.MoveMotherNature.ordinal()){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    c.play();
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta

                    ((Character4) c).enableEffect();
                }
            }
        }
    }

    public void playCharacterCard5(int playerIndex, int cardIndex, int islandIndex){
        if(playerIndex == currentPlayer){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    c.play();
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta

                    gameBoard.getIslands().get(islandIndex).addNoEntry();
                }
            }
        }
    }

    public void playCharacterCard6(int playerIndex, int cardIndex){
        if(playerIndex == currentPlayer && currentPhase.ordinal() <= GamePhase.MoveStudents.ordinal()){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    c.play();
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta

                    ((Character6) c).enableEffect();
                }
            }
        }
    }

    public void playCharacterCard8(int playerIndex, int cardIndex){
        if(playerIndex == currentPlayer && currentPhase.ordinal() <= GamePhase.MoveStudents.ordinal()){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    c.play();
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta

                    ((Character8) c).enableEffect();
                }
            }
        }
    }

    public void playCharacterCard11(int playerIndex, int cardIndex, int colorIndex){
        if(playerIndex == currentPlayer){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost() && ((Character11) c).checkColorExists(colorIndex)){
                    c.play();
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta

                    players.get(playerIndex).getPlayerSchoolBoard().getDiningRoom().addStudent(((Character11) c).getStudent(colorIndex));
                    ((Character11) c).addStudent(gameBoard.getBag().draw());
                }
            }
        }
    }

    public void playCharacterCard12(int playerIndex, int cardIndex, int colorIndex){
        if(playerIndex == currentPlayer){
            for (CharacterCard c: gameBoard.getThreeCharacterCards()){
                if(cardIndex == c.getIndex() && players.get(playerIndex).getCoins() >= c.getCost()){
                    c.play();
                    gameBoard.addCoinsToGeneralReserve(c.getCost() - 1); //meno uno perchè una va sulla carta

                    for (Player p: players)
                            gameBoard.getBag().fill(p.getPlayerSchoolBoard().getDiningRoom().removeThreeStudents(Colour.values()[colorIndex]));
                }
            }
        }
    }
}