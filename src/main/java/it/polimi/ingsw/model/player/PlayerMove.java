package it.polimi.ingsw.model.player;

import it.polimi.ingsw.view.View;


public class PlayerMove {

    private final int param1;
    private final int param2;
    private final int param3;
    private final Player player;
    private final View view;

    public PlayerMove(Player player, int param1, int param2, View view) {
        this.player = player;
        this.view = view;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = -1;
    }

    public PlayerMove(Player player, int param1, int param2, int param3, View view) {
        this.player = player;
        this.view = view;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    public Player getPlayer() {
        return player;
    }

    public View getView() {
        return view;
    }

    public int getParam1() {
        return param1;
    }

    public int getParam2() {
        return param2;
    }

    public int getParam3() {
        return param3;
    }
}

