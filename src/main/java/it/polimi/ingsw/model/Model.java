package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.gameboard.GameBoard;
import it.polimi.ingsw.model.player.Player;

import java.util.ArrayList;

public class Model {
    private ArrayList<Player> players;
    private GameBoard gameBoard;

    private Player winner;

    private int currentPlayer;
    private GamePhase currentPhase;
    private Player[] playersTurnOrder;
    private int phaseCounter;
    private int playerPhaseCounter;

    //Constructor Game creates a new Game instance
    public Model() {
        this.players = new ArrayList<>();
        this.gameBoard = new GameBoard();

        this.playersTurnOrder = new Player[Parameters.numPlayers];
        this.phaseCounter = 0;
        this.playerPhaseCounter = 0;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GamePhase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(GamePhase currentPhase) {
        this.currentPhase = currentPhase;
    }

    public Player[] getPlayersTurnOrder() {
        return playersTurnOrder;
    }

    public void setPlayersTurnOrder(int i, Player playersTurnOrder) {
        this.playersTurnOrder[i] = playersTurnOrder;
    }

    public int getPhaseCounter() {
        return phaseCounter;
    }

    public void setPhaseCounter(int phaseCounter) {
        this.phaseCounter = phaseCounter;
    }

    public int getPlayerPhaseCounter() {
        return playerPhaseCounter;
    }

    public void setPlayerPhaseCounter(int playerPhaseCounter) {
        this.playerPhaseCounter = playerPhaseCounter;
    }
}
