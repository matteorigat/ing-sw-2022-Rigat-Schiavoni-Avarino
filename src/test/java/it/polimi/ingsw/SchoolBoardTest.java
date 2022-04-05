package it.polimi.ingsw;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.SchoolBoard;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Island;

import static org.junit.jupiter.api.Assertions.*;
import junit.framework.TestCase;
import org.junit.Test;

public class SchoolBoardTest {

    @Test
    public void SchoolBoardInitTest(){
        Parameters.setParameters(2, true);
        SchoolBoard sb = new SchoolBoard(TowerColour.Black);
        Island isl = new Island(0);

        assertEquals(0, sb.getStudentsEntrance().size());
        assertEquals(0, sb.getProfessors().size());
        assertEquals(8, sb.getTowers().size());

        Student student1 = new Student(Colour.Green);
        Student student2 = new Student(Colour.Red);
        Student student3 = new Student(Colour.Blue);
        Student student4 = new Student(Colour.Pink);
        Student student5 = new Student(Colour.Yellow);
        Student student6 = new Student(Colour.Red);
        Student student7 = new Student(Colour.Pink);

        sb.getStudentsEntrance().add(student1);
        sb.getStudentsEntrance().add(student2);
        sb.getStudentsEntrance().add(student3);
        sb.getStudentsEntrance().add(student4);
        sb.getStudentsEntrance().add(student5);
        sb.getStudentsEntrance().add(student6);
        sb.getStudentsEntrance().add(student7);

        assertEquals(7, sb.getStudentsEntrance().size());

        sb.addProfessor(Colour.Green);
        sb.addProfessor(Colour.Yellow);
        assertEquals(2, sb.getProfessors().size());

        assertEquals(Colour.Green, sb.getProfessors().get(0).getProfessorColour());

        sb.removeProfessor(sb.getProfessors().get(0).getProfessorColour());
        assertEquals(1, sb.getProfessors().size());
        assertEquals(Colour.Yellow, sb.getProfessors().get(0).getProfessorColour());

        sb.moveStudentToDiningRoom(Colour.Pink.ordinal());
        assertEquals(6, sb.getStudentsEntrance().size());
        sb.moveStudentToIsland(Colour.Pink.ordinal(), isl);
        assertEquals(5, sb.getStudentsEntrance().size());
        sb.moveStudentToDiningRoom(Colour.Pink.ordinal());
        assertEquals(5, sb.getStudentsEntrance().size());

    }
}
