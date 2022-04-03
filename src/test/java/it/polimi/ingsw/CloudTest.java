package it.polimi.ingsw;
import it.polimi.ingsw.exceptions.TooManyStudentsOnCloudException;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.gameboard.Cloud;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;



public class CloudTest extends TestCase {

    @Test
    public void testCloud(){
        Cloud cloud = new Cloud();
        Parameters.setParameters(3,false);
        Student student1 = new Student(Colour.Green);
        Student student2 = new Student(Colour.Red);
        Student student3 = new Student(Colour.Blue);
        Student student4 = new Student(Colour.Pink);
        Student student5 = new Student(Colour.Yellow);
        boolean thrown = false;

        ArrayList<Student> stud =  cloud.getStudents();
        assertEquals(stud.size(),0);
        try
        {
            cloud.addStudent(student1);
        cloud.addStudent(student2);
        cloud.addStudent(student3);
        cloud.addStudent(student4);
        }
        catch(TooManyStudentsOnCloudException e){}
        stud= cloud.getStudents();
        assertEquals(stud.size(),4);
        stud= cloud.getStudents();
        assertEquals(stud.size(),0);
        try
        {
            cloud.addStudent(student1);

        cloud.addStudent(student2);
        cloud.addStudent(student3);
        cloud.addStudent(student4);
        cloud.addStudent(student5); }
        catch (TooManyStudentsOnCloudException e){
            thrown = true;
        }

        stud= cloud.getStudents();
        assertEquals(stud.size(),4);
        assertEquals(thrown,true);



    }
}
