package it.polimi.ingsw;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class StudentTest extends TestCase{
    @Test
    public void testStudent(){
        Student student = new Student(Colour.Green);
        assertEquals(student.getColour(),Colour.Green);
        assertEquals(Colour.Green,student.getColour());

    }
}
