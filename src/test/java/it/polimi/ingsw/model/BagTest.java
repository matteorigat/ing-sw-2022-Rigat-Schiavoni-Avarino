package it.polimi.ingsw.model;

import it.polimi.ingsw.model.player.Student;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.gameboard.Bag;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Test;

public class BagTest{
    @Test
    public void testBag()

    {
        Bag bag = new Bag();
        Student student = new Student(Colour.Green);
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(student);
        bag.fill(arrayList);
        Student student1 = bag.draw();
        assertEquals(student1,student);
        assertEquals(student1.getColour(),Colour.Green);
        assertEquals(bag.draw(),null);
        assertEquals(bag.getSize(),0);

    }
}
