package it.polimi.ingsw;


import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.gameboard.Bag;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class BagTest extends TestCase{
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
