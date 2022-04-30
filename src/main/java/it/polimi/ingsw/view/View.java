package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.server.PlayerMove;

public abstract class View extends Observable<PlayerMove> implements Observer<Model> {

    private Player player;

    protected View(Player player){
        this.player = player;
    }

    protected Player getPlayer(){
        return player;
    }

    protected abstract void showMessage(Object message);

    void handleMove(int row, int column) {
        System.out.println(row + " " + column);
        notify(new PlayerMove());
    }

    public void reportError(String message){
        showMessage(message);
    }

}
