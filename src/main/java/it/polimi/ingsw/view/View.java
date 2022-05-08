package it.polimi.ingsw.view;

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

    void handleMove(int param1, int param2) {
        System.out.println(param1 + " " + param2);
        notify(new PlayerMove(player, param1, param2, this));
    }

    void handleMove(int param1, int param2, int param3) {
        System.out.println(param1 + " " + param2 + " " + param3);
        notify(new PlayerMove(player, param1, param2, param3, this));
    }

    void handleMove(int param1, int param2, int param3, int param4) {
        System.out.println(param1 + " " + param2 + " " + param3 + " " + param4);
        notify(new PlayerMove(player, param1, param2, param3, param4, this));
    }

    public void reportError(String message){
        showMessage(message);
    }

}
