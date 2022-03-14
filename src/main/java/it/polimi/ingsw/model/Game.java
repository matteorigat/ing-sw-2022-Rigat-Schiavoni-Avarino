package it.polimi.ingsw.model;

import java.util.ArrayList;

/*
 * In this class we manage the main actions of the match.
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

    //Gets a player by nickname
    public Player getPlayer(String nickname) throws NotExistingPlayerException{
        for (Player p:players) {
            if(p.getNickname().equals(nickname))
                return  p;
        }
        throw  new NotExistingPlayerException();
    }

    //Gets the gameBoard instance
    public GameBoard getGameBoard() {
        return gameBoard;
    }

}
