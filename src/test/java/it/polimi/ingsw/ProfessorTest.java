package it.polimi.ingsw;

import it.polimi.ingsw.model.Professor;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static it.polimi.ingsw.model.enumeration.Colour.*;
import static it.polimi.ingsw.model.enumeration.Colour.Blue;

public class ProfessorTest extends TestCase {



        @Test
        public void getProfessorColourTest() {

            Professor prGreen = new Professor(Green);
            Professor prRed = new Professor(Red);
            Professor prYellow = new Professor(Yellow);
            Professor prPink = new Professor(Pink);
            Professor prBlue = new Professor(Blue);


            Assert.assertEquals(Green, prGreen.getProfessorColour());
            Assert.assertEquals(Red, prRed.getProfessorColour());
            Assert.assertEquals(Yellow, prYellow.getProfessorColour());
            Assert.assertEquals(Pink, prPink.getProfessorColour());
            Assert.assertEquals(Blue, prBlue.getProfessorColour());

        }


    }
