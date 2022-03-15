package it.polimi.ingsw;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.gameboard.Bag;

/**
 *  Hello world!
 *
 **/

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Bag b = new Bag();
        Game g = new Game();

        g.init(b);
        for(int i=0; i<9; i++){
            Student s = b.draw();
            System.out.println(s);
        }

    }
}
