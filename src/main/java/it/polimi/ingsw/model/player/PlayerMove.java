package it.polimi.ingsw.model.player;

import it.polimi.ingsw.view.View;


/** PlayerMove class permits us to perform every move coming from the player (client-side)
 * - every move gets at most 8 integer parameters (it depends on the type of move),
 * Player attribute to know who is making the move
 * and View attribute to update it.   */
public class PlayerMove {

    private final int param1;
    private final int param2;
    private final int param3;
    private final int param4;
    private final int param5;
    private final int param6;
    private final int param7;
    private final int param8;
    private final Player player;
    private final View view;

    public PlayerMove(Player player, int param1, int param2, View view) {
        this.player = player;
        this.view = view;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = -1;
        this.param4 = -1;
        this.param5 = -1;
        this.param6 = -1;
        this.param7 = -1;
        this.param8 = -1;
    }

    public PlayerMove(Player player, int param1, int param2, int param3, View view) {
        this.player = player;
        this.view = view;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = -1;
        this.param5 = -1;
        this.param6 = -1;
        this.param7 = -1;
        this.param8 = -1;
    }

    public PlayerMove(Player player, int param1, int param2, int param3, int param4, View view) {
        this.player = player;
        this.view = view;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
        this.param5 = -1;
        this.param6 = -1;
        this.param7 = -1;
        this.param8 = -1;
    }

    public PlayerMove(Player player, int param1, int param2, int param3, int param4, int param5, int param6, View view) {
        this.player = player;
        this.view = view;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
        this.param5 = param5;
        this.param6 = param6;
        this.param7 = -1;
        this.param8 = -1;
    }

    public PlayerMove(Player player, int param1, int param2, int param3, int param4, int param5, int param6, int param7, int param8, View view) {
        this.player = player;
        this.view = view;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
        this.param5 = param5;
        this.param6 = param6;
        this.param7 = param7;
        this.param8 = param8;
    }


    /**
     * Method getPlayer returns the Player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Method getView returns the View
     * @return view
     */
    public View getView() {
        return view;
    }

    /**
     * Method getParam1 returns param1
     * @return param1
     */
    public int getParam1() {
        return param1;
    }

    /**
     * Method getParam2 returns param2
     * @return param2
     */

    public int getParam2() {
        return param2;
    }


    /**
     * Method getParam3 returns param3
     * @return param3
     */
    public int getParam3() {
        return param3;
    }


    /**
     * Method getParam4 returns param4
     * @return param4
     */
    public int getParam4() {
        return param4;
    }


    /**
     * Method getParam5 returns param5
     * @return param5
     */
    public int getParam5() {
        return param5;
    }

    /**
     * Method getParam6 returns param6
     * @return param6
     */
    public int getParam6() {
        return param6;
    }

    /**
     * Method getParam7 returns param7
     * @return param7
     */
    public int getParam7() {
        return param7;
    }

    /**
     * Method getParam8 returns param8
     * @return param8
     */
    public int getParam8() {
        return param8;
    }
}

