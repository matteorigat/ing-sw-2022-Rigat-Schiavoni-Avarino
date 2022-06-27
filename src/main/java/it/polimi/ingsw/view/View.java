package it.polimi.ingsw.view;

import it.polimi.ingsw.model.player.MoveMessage;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.model.player.PlayerMove;

/**
 * View is an abstract class and it is important to manage
 * every move played in the match by each player.
 */
public abstract class View extends Observable<PlayerMove> implements Observer<MoveMessage> {

    private Player player;

    /**
     * View constructor
     * @param player
     */
    protected View(Player player){
        this.player = player;
    }

    /**
     * getPlayer method returns the player
     * @return player
     */
    protected Player getPlayer(){
        return player;
    }

    /**
     * showMessage method sends the Model changes to its specific Client.
     * @param message
     */
    protected abstract void showMessage(Object message);


    /**
     * We have different type of moves. They all use integer parameters but every specific
     * move uses a different number of parameters so we have to handle every kind of move.
     */

    /**
     * HandleMove method handles the received move with 2 parameters
     * @param param1
     * @param param2
     */
    void handleMove(int param1, int param2) {
        System.out.println(param1 + " " + param2);
        notify(new PlayerMove(player, param1, param2, this));
    }

    /**
     * HandleMove method handles the received move with 3 parameters
     * @param param1
     * @param param2
     * @param param3
     */
    void handleMove(int param1, int param2, int param3) {
        System.out.println(param1 + " " + param2 + " " + param3);
        notify(new PlayerMove(player, param1, param2, param3, this));
    }

    /**
     * HandleMove method handles the received move with 4 parameters
     * @param param1
     * @param param2
     * @param param3
     * @param param4
     */
    void handleMove(int param1, int param2, int param3, int param4) {
        System.out.println(param1 + " " + param2 + " " + param3 + " " + param4);
        notify(new PlayerMove(player, param1, param2, param3, param4, this));
    }

    /**
     * HandleMove method handles the received move with 5 parameters
     * @param param1
     * @param param2
     * @param param3
     * @param param4
     * @param param5
     * @param param6
     */
    void handleMove(int param1, int param2, int param3, int param4, int param5, int param6) {
        System.out.println(param1 + " " + param2 + " " + param3 + " " + param4 + " " + param5 + " " + param6);
        notify(new PlayerMove(player, param1, param2, param3, param4, param5, param6,this));
    }

    /**
     * HandleMove method handles the received move with 6 parameters
     * @param param1
     * @param param2
     * @param param3
     * @param param4
     * @param param5
     * @param param6
     * @param param7
     * @param param8
     */
    void handleMove(int param1, int param2, int param3, int param4, int param5, int param6, int param7, int param8) {
        System.out.println(param1 + " " + param2 + " " + param3 + " " + param4 + " " + param5 + " " + param6 + " " + param7 + " " + param8);
        notify(new PlayerMove(player, param1, param2, param3, param4, param5, param6, param7, param8, this));
    }

    /**
     * reportError method reports an Error message to the Player whenever he tries to perform an unpermitted move.
     * @param message
     */
    public void reportError(String message){
        showMessage(message);
    }

}
