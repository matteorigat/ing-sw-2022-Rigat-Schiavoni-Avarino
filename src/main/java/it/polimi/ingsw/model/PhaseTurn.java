package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.player.Player;

public class PhaseTurn {

    private int currentPlayer;
    private GamePhase currentPhase;
    private Player[] playersTurnOrder;
    private int phaseCounter;
    private int playerPhaseCounter;

    public PhaseTurn() {
        phaseCounter = 0;
        playerPhaseCounter = 0;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public GamePhase getCurrentPhase() {
        return currentPhase;
    }

    public Player[] getPlayersTurnOrder() {
        return playersTurnOrder;
    }

    public int getPhaseCounter() {
        return phaseCounter;
    }

    public int getPlayerPhaseCounter() {
        return playerPhaseCounter;
    }
}
