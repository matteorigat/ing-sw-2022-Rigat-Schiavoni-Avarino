package it.polimi.ingsw.model;

public class Player {
    private final String nickname;
    private final SchoolBoard playerSchoolBoard;


    //Constructor Player creates a new Player instance
    public Player(String nickname, SchoolBoard playerSchoolBoard) {
        this.nickname = nickname;
        this.playerSchoolBoard = playerSchoolBoard;
    }

    //Gets the nickname of the player
    public String getNickname() {
        return nickname;
    }
    //Gets the schoolBoard of the player
    public SchoolBoard getPlayerSchoolBoard() {
        return playerSchoolBoard;
    }
}


