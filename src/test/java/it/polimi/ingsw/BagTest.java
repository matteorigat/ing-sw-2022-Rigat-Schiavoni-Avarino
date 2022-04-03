package it.polimi.ingsw;

import it.polimi.ingsw.model.AssistantCard;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.gameboard.Bag;
import junit.framework.TestCase;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import junit.framework.TestCase;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BagTest extends TestCase{
    @Test
    public void testBag()

    {
        Bag bag = new Bag();
        Student student = new Student(Colour.Green);
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(student);
        bag.fill(arrayList);
        assertEquals(bag.draw(),student);
        assertEquals(bag.draw(),null);

    }
}
