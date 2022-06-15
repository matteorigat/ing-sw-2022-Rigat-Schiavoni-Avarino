package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.Model;


/**
 * Move Message class permits us to communicate with the model sending him the
 * status of the game everytime something happens.
 * infact in every moveMessage is sent the model and a player.
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
     * getPlayer method returns a player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * getModel method returns the model
     * @return model
     */

    public Model getModel() {
        return model;
    }

}

