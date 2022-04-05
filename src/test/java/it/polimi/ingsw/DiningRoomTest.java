package it.polimi.ingsw;

import it.polimi.ingsw.model.DiningRoom;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;

import static org.junit.jupiter.api.Assertions.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;


public class DiningRoomTest {

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
    }
}
