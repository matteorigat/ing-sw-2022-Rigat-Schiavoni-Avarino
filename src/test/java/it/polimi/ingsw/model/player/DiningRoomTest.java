package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.player.DiningRoom;
import it.polimi.ingsw.model.enumeration.Colour;
import java.util.ArrayList;

import static org.junit.Assert.*;

import it.polimi.ingsw.model.player.Student;
import org.junit.Test;

public class DiningRoomTest{

    @Test
    public void testStudentsDiningRoom(){

        DiningRoom dr = new DiningRoom();
        Student student1 = new Student(Colour.Green);
        Student student2 = new Student(Colour.Red);
        Student student3 = new Student(Colour.Blue);
        Student student4 = new Student(Colour.Pink);
        Student student5 = new Student(Colour.Yellow);

        assertEquals(0, dr.numOfStudentByColor(Colour.Green));
        assertEquals(0, dr.numOfStudentByColor(Colour.Red));
        assertEquals(0, dr.numOfStudentByColor(Colour.Blue));
        assertEquals(0, dr.numOfStudentByColor(Colour.Pink));
        assertEquals(0, dr.numOfStudentByColor(Colour.Yellow));

        assertFalse(dr.addStudent(student1));
        assertFalse(dr.addStudent(student2));
        assertFalse(dr.addStudent(student3));
        assertFalse(dr.addStudent(student4));
        assertFalse(dr.addStudent(student5));

        assertEquals(1, dr.numOfStudentByColor(Colour.Green));
        assertEquals(1, dr.numOfStudentByColor(Colour.Red));
        assertEquals(1, dr.numOfStudentByColor(Colour.Blue));
        assertEquals(1, dr.numOfStudentByColor(Colour.Pink));
        assertEquals(1, dr.numOfStudentByColor(Colour.Yellow));

        assertFalse(dr.addStudent(student1));
        assertFalse(dr.addStudent(student2));
        assertFalse(dr.addStudent(student3));
        assertFalse(dr.addStudent(student4));
        assertFalse(dr.addStudent(student5));

        assertTrue(dr.addStudent(student1));
        assertTrue(dr.addStudent(student2));
        assertTrue(dr.addStudent(student3));
        assertTrue(dr.addStudent(student4));
        assertTrue(dr.addStudent(student5));

        assertFalse(dr.addStudent(student1));
        assertFalse(dr.addStudent(student2));
        assertFalse(dr.addStudent(student3));
        assertFalse(dr.addStudent(student4));
        assertFalse(dr.addStudent(student5));

        assertFalse(dr.addStudent(student1));
        assertFalse(dr.addStudent(student2));
        assertFalse(dr.addStudent(student3));
        assertFalse(dr.addStudent(student4));
        assertFalse(dr.addStudent(student5));

        assertTrue(dr.addStudent(student1));
        assertTrue(dr.addStudent(student2));
        assertTrue(dr.addStudent(student3));
        assertTrue(dr.addStudent(student4));
        assertTrue(dr.addStudent(student5));

        assertFalse(dr.addStudent(student1));
        assertFalse(dr.addStudent(student2));
        assertFalse(dr.addStudent(student3));
        assertFalse(dr.addStudent(student4));
        assertFalse(dr.addStudent(student5));

        assertFalse(dr.addStudent(student1));
        assertFalse(dr.addStudent(student2));
        assertFalse(dr.addStudent(student3));
        assertFalse(dr.addStudent(student4));
        assertFalse(dr.addStudent(student5));

        assertTrue(dr.addStudent(student1));
        assertTrue(dr.addStudent(student2));
        assertTrue(dr.addStudent(student3));
        assertTrue(dr.addStudent(student4));
        assertTrue(dr.addStudent(student5));

        assertFalse(dr.addStudent(student1));
        assertFalse(dr.addStudent(student2));
        assertFalse(dr.addStudent(student3));
        assertFalse(dr.addStudent(student4));
        assertFalse(dr.addStudent(student5));

        assertEquals(10, dr.numOfStudentByColor(Colour.Green));
        assertEquals(10, dr.numOfStudentByColor(Colour.Red));
        assertEquals(10, dr.numOfStudentByColor(Colour.Blue));
        assertEquals(10, dr.numOfStudentByColor(Colour.Pink));
        assertEquals(10, dr.numOfStudentByColor(Colour.Yellow));

        for(int i=0; i<20; i++){
            assertFalse(dr.addStudent(student1));
            assertFalse(dr.addStudent(student2));
            assertFalse(dr.addStudent(student3));
            assertFalse(dr.addStudent(student4));
            assertFalse(dr.addStudent(student5));
        }

        assertEquals(10, dr.numOfStudentByColor(Colour.Green));
        assertEquals(10, dr.numOfStudentByColor(Colour.Red));
        assertEquals(10, dr.numOfStudentByColor(Colour.Blue));
        assertEquals(10, dr.numOfStudentByColor(Colour.Pink));
        assertEquals(10, dr.numOfStudentByColor(Colour.Yellow));

        ArrayList<Student> threeStudents = new ArrayList<>();
        threeStudents = dr.removeThreeStudents(Colour.Green);

        assertEquals(3, threeStudents.size());

        assertEquals(7, dr.numOfStudentByColor(Colour.Green));
        assertEquals(10, dr.numOfStudentByColor(Colour.Red));
        assertEquals(10, dr.numOfStudentByColor(Colour.Blue));
        assertEquals(10, dr.numOfStudentByColor(Colour.Pink));
        assertEquals(10, dr.numOfStudentByColor(Colour.Yellow));

        dr.removeThreeStudents(Colour.Green);
        dr.removeThreeStudents(Colour.Red);
        dr.removeThreeStudents(Colour.Blue);
        dr.removeThreeStudents(Colour.Pink);
        dr.removeThreeStudents(Colour.Yellow);

        assertEquals(4, dr.numOfStudentByColor(Colour.Green));
        assertEquals(7, dr.numOfStudentByColor(Colour.Red));
        assertEquals(7, dr.numOfStudentByColor(Colour.Blue));
        assertEquals(7, dr.numOfStudentByColor(Colour.Pink));
        assertEquals(7, dr.numOfStudentByColor(Colour.Yellow));

    }
}
