package it.polimi.ingsw;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.gameboard.Bag;

/**
 *   Hello world!
 *
 **/

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!");
        Game game = new Game();
        Bag b = new Bag();

        String nickname = null;  // qui bisogna fare qualcosa per prendere in lettura i vari nickname

        boolean isfull = false;
        while(isfull){  // condizione a caso, da cambiare

            game.CreateNewPlayer(nickname);
        }
        game.init(b);

        for(int i=0; i<10; i++){
            Student s = b.draw();
            System.out.println(s + "" + i);
        }

    }
}
