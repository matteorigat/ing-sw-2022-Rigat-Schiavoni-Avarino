package it.polimi.ingsw;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.LocalViewTest;

/**
 *   Hello world!
 *
 **/

public class App 
{
    public static void main( String[] args )
    {
       /* System.out.println( "Hello World!");
        Game game = new Game();
        Bag b = new Bag();
        String nickname = "carlo";  // qui bisogna fare qualcosa per prendere in lettura i vari nickname


        for(int i = 0; i< Parameters.numPlayers; i++){ // Ha senso?
            game.addPlayer(nickname);
        }

        game.init(); */

        /*
        for(int i=0; i<10; i++){
            Student s = b.draw();
            System.out.println(s + "" + i);
        }
        */

        Controller controller = new Controller();
        controller.setParameters(2, true);
        controller.addPlayer("Gius");
        controller.addPlayer("Mef");
        //controller.addPlayer("Nico");
        controller.init();
        LocalViewTest view = new LocalViewTest();
        view.setController(controller);
        view.start();

    }
}
