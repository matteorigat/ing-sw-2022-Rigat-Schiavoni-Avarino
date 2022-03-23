package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.NotExistingPlayerException;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Bag;
import it.polimi.ingsw.model.gameboard.Cloud;
import it.polimi.ingsw.model.gameboard.GameBoard;

import java.util.ArrayList;
import it.polimi.ingsw.model.enumeration.Colour;

/*
 *  In this class we manage the main actions of the match.
 */

public class Game {
    private final ArrayList<Player> players;
    private final GameBoard gameBoard;
    private Player currentPlayer;


    //Constructor Game creates a new Game instance
    public Game() {
        players = new ArrayList<>();
        gameBoard = new GameBoard();
        currentPlayer = null;
    }

    //Gets the players of the match
    public ArrayList<Player> getPlayers() {
        return players;
    }


    //Gets the gameBoard instance
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setNumPlayers(int num){
        Parameters.setParameters(num);
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
        for (AssistantCard as : players.get(playerIndex).getAssistantDeck())
            if (as.getValue() == priority)
                players.get(playerIndex).playCard(as);
    }


    //Fase azione punto 1
    public void moveStudentToIsland(int playerIndex, int colour, int IslandPosition){
        players.get(playerIndex).getPlayerSchoolBoard().moveStudentToIsland(colour, this.gameBoard.getIslands().get(IslandPosition));
    }
    //Fase azione punto 1
    public void moveStudentToDiningRoom(int playerIndex, int colour){
        players.get(playerIndex).getPlayerSchoolBoard().moveStudentToDiningRoom(colour);
    }

    //Fase azione punto 2.1
    public void moveMotherNature(int playerIndex, int movements){ //devo controllare se player ha i permessi
        if(movements > 0){
            int newPos = ((this.gameBoard.getMotherNature() + movements)%Parameters.numIsland);
            this.gameBoard.setMotherNature(newPos);//moves motherNature by specified movements
            this.checkIslandInfluence(newPos);
        }
    }
    //Fase azione punto 2.2 //vedo chi controlla l'isola, se il player è cambiato sostituisco le torri
    public void checkIslandInfluence(int islandIndex){

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
    public void checkIslandFusion(int islandIndex){
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
        ArrayList<Student> stud = this.gameBoard.getClouds().get(cloudPosition).getStudents();

        for (Student s: stud){ //qui e alla fine del metodo init() farei un nuovo metodo add che controlla anche se non viene superato il limite di studenti all'entrata
            players.get(playerIndex).getPlayerSchoolBoard().getStudentsEntrance().add(s);
        }
    }

    public int getCurrentPlayer(){
        return players.indexOf(currentPlayer);
    }
}