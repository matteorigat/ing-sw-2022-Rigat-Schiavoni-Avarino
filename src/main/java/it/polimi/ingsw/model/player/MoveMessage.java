package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.Model;


/**
 * Move Message class permits us to communicate with the Clients sending them the
 * status of the game everytime something changes.
 * In fact a moveMessage contains the Model and a Player.
 */
public class MoveMessage {

    private final Player player;

    private final Model model;

    /**
     * MoveMessage constructor
     * @param model
     * @param player
     */
    public MoveMessage(Model model, Player player) {
        this.player = player;
        this.model = model;
    }

    /**
     * getPlayer method returns the player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * getModel method returns the Model
     * @return model
     */

    public Model getModel() {
        return model;
    }

}

