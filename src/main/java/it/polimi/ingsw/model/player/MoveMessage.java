package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.Model;


public class MoveMessage {

    private final Player player;

    private final Model model;

    public MoveMessage(Model model, Player player) {
        this.player = player;
        this.model = model;
    }

    public Player getPlayer() {
        return player;
    }

    public Model getModel() {
        return model;
    }

}

