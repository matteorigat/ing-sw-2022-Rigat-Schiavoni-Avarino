package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.TowerColour;

import java.util.ArrayList;

public class Player {
    private String nickname;
    private SchoolBoard playerSchoolBoard;


    //Constructor Player creates a new Player instance
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


}




