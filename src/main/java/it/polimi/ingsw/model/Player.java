package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.TowerColour;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
    private String nickname;
    private SchoolBoard playerSchoolBoard;







    // Constructor Player creates a new Player instance
    public Player(String nickname) {
        this.nickname = nickname;
        this.playerSchoolBoard = new SchoolBoard();
    }




    //Return the player's tower color
    public TowerColour PlayerTowerColor(){
        return playerSchoolBoard.getTowerColor();
    }

    public ArrayList<Professor> getPlayerProfessors(){
        return playerSchoolBoard.getProfessors();
    }

    //Gets the schoolBoard of the player
    public SchoolBoard getPlayerSchoolBoard() {
        return playerSchoolBoard;
    }

    //every player has an assistant deck
    AssistantDeck assistantDeck = new AssistantDeck();



}




