package it.polimi.ingsw.model.player;

import it.polimi.ingsw.view.View;


public class PlayerMove {

    private final Player player;
    private final View view;

    public PlayerMove(Player player, View view) {
        this.player = player;
        this.view = view;
    }

    public Player getPlayer() {
        return player;
    }

    public View getView() {
        return view;
    }


}

