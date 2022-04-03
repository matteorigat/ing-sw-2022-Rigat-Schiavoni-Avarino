package it.polimi.ingsw;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Bag;
import it.polimi.ingsw.model.gameboard.Cloud;
import it.polimi.ingsw.model.gameboard.GameBoard;
import it.polimi.ingsw.model.gameboard.Island;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameBoardTest extends TestCase {

    @Test
    public void testGameBoard(){
        Parameters.setParameters(3,false);
        GameBoard gb = new GameBoard();
        gb.setMotherNature(1);
        assertEquals(gb.getMotherNature(),1);
        assertEquals(gb.getGeneralReserve(),17);
        gb.getOneCoin();
        assertEquals(gb.getGeneralReserve(),16);
        gb.addCoinsToGeneralReserve(1);
        assertEquals(gb.getGeneralReserve(),17);
        Student student = new Student(Colour.Green);
        gb.addStudentOnIsland(0,student);
        Island island = gb.getIslands().get(0);
        ArrayList<Student> stud = island.getStudents();
        Student student1 = stud.get(0);
        assertEquals(student1.getColour(),Colour.Green);
        boolean bool = false;
        if(stud.contains(student)){
            bool = true;
        }
       assertTrue(bool);
        gb.addStudentOnCloud(0,student);
        Cloud c = gb.getClouds().get(0);
        student1 = c.getStudents().get(0);
        assertEquals(student1,student);
        Bag bag = gb.getBag();
        assertEquals(bag.getSize(),0);
        ArrayList<Student> stud1 = new ArrayList<>();
        stud1.add(student);
        Student student2 = new Student(Colour.Pink);
        stud1.add(student2);
        bag.fill(stud1);
        assertEquals(bag.getSize(),2);
        ArrayList<Island> islands = gb.getIslands();
        assertEquals(islands.size(),12);
        Island island1 = islands.get(5);
        island1.changeTowerColor(TowerColour.Black);
        island1.setNumTower(1);
        island1.addStudent(student1);
        Island island2 = islands.get(6);
        island2.changeTowerColor(TowerColour.Black);
        island2.setNumTower(2);
        island2.addStudent(student2);
        Island island3 = islands.get(7);
        gb.islandFusion(5,6);
        assertEquals(islands.size(),11);
        Island island4 = islands.get(6);
        assertEquals(island1.getNumTower(),3);
        assertEquals(island1.getTowerColor(),TowerColour.Black);
        assertEquals(island3,island4);


        boolean bool2 = false;
        if(island1.getStudents().contains(student1)
                &&
                island1.getStudents().contains(student2)){
            bool2 = true;
        }
        assertTrue(bool2);

    }
}
