package it.polimi.ingsw.controller;

import it.polimi.ingsw.exceptions.NotExistingPlayerException;
import it.polimi.ingsw.model.AssistantCard;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Student;
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


    //Constructor Game creates a new Game instance
    public Game() {
        players = new ArrayList<>();
        gameBoard = new GameBoard();
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

    public void PlanningPhase(){
        for(Cloud c: gameBoard.getClouds()){    // a inizio della fase di pianificazione aggiungo gli studenti alle isole
            for(int i=0; i<Parameters.numCloudStudents; i++){
                gameBoard.addStudentOnCloud(i, gameBoard.getBag().draw());
            }
        }
    }

    public void playAssistantCard(int prioriy, int playerIndex){
        for (AssistantCard as : players.get(playerIndex).getAssistantDeck()){
            if ( as.getValue() == (prioriy) ) {
                players.get(playerIndex).playCard(as);


            }
        }

    }
    public void ActionPhase(int playerIndex){

    }

    public void moveMotherNature(int movements){
        this.gameBoard.setMotherNature(this.gameBoard.getMotherNature() + movements);    //moves motherNature by specified movements
    }


    public void moveStudentToIsland(int position, int player, Colour colour){

    }

    public void moveStudentToDiningRoom(int position, int player,Colour colour){

    }
}