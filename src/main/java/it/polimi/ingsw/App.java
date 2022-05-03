package it.polimi.ingsw;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.view.LocalView;

/**
 *   Hello world!
 *
 **/

public class App 
{
    public static void main( String[] args )
    {
        Controller controller = new Controller();
        controller.setParameters(2, true);
        Player p1 = new Player("mef", 0);
        Player p2 = new Player("gius", 1);
        controller.addPlayer(p1);
        controller.addPlayer(p2);
        //controller.addPlayer("Nico");
        controller.init();
        LocalView view = new LocalView();
        view.setController(controller);
        view.start();

    }
}
