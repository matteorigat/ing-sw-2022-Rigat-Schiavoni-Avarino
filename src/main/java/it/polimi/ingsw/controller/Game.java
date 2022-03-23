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
    public void playAssistantCard(int priority, int playerIndex){
        for (AssistantCard as : players.get(playerIndex).getAssistantDeck())
            if (as.getValue() == priority)
                players.get(playerIndex).playCard(as);
    }


    //Fase azione punto 1
    public void moveStudentToIsland(int IslandPosition, int player, int colour){
        players.get(player).getPlayerSchoolBoard().moveStudentToIsland(colour, this.gameBoard.getIslands().get(IslandPosition));
    }
    //Fase azione punto 1
    public void moveStudentToDiningRoom(int player,int colour){
        players.get(player).getPlayerSchoolBoard().moveStudentToDiningRoom(colour);
    }

    //Fase azione punto 2
    public void moveMotherNature(int movements){
        int newPos = ((this.gameBoard.getMotherNature() + movements)%Parameters.numIsland);
        this.gameBoard.setMotherNature(newPos);//moves motherNature by specified movements
        this.checkIsland(newPos);
    }

    public void checkIsland(int islandIndex){
        // vedo chi controllava l'isola prima
        Player oldPlayer = null;
        for (Player pl: players){
            if(pl.PlayerTowerColor().equals(this.gameBoard.getIslands().get(islandIndex).getTowerColor())){
                oldPlayer = pl;
            }
        }

        // vedo chi ha più influenza ora
        Player newPlayer = this.gameBoard.getIslands().get(islandIndex).Influence(this.players);

        //ridò al vecchio giocatore le sue torri e le sostituisco con quelle del nuovo giocatore
        if (newPlayer!=null){
           for(int i=0; i<=this.gameBoard.getIslands().get(islandIndex).getNumTower(); i++){
               oldPlayer.getPlayerSchoolBoard().addTower(oldPlayer.PlayerTowerColor());
               newPlayer.getPlayerSchoolBoard().getTowers().remove(0);
           }
           this.getGameBoard().getIslands().get(islandIndex).changeTowerColor(newPlayer.PlayerTowerColor());

        } else return; //se non c'è ancora una torre sull'isola o in caso di parità non succede nulla
    }


    public int getCurrentPlayer(){
      return players.indexOf(currentPlayer);
    }
}