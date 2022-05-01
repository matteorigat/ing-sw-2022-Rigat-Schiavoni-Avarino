package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.player.MoveMessage;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.model.player.PlayerMove;

public abstract class View extends Observable<PlayerMove> implements Observer<MoveMessage> {

    private Player player;

    protected View(Player player){
        this.player = player;
    }

    protected Player getPlayer(){
        return player;
    }

    protected abstract void showMessage(Object message);

    void handleMove() {
        notify(new PlayerMove(player, this));
    }

    public void reportError(String message){
        showMessage(message);
    }

}
