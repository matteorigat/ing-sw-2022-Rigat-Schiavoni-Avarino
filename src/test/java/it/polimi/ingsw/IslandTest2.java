package it.polimi.ingsw;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Island;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class IslandTest2 {
    @Test
    public void islandTestMethods(){
        Parameters.setParameters(2, true);
        Player p1 = new Player("mef", 0);
        Player p2 = new Player("gius", 1);
        ArrayList<Player> arrayPlayers = new ArrayList<>();
        arrayPlayers.add(p1);
        arrayPlayers.add(p2);

        Island island = new Island(5);
        Student student1 = new Student(Colour.Green);
        Student student2 = new Student(Colour.Green);
        Student student3 = new Student(Colour.Yellow);
        Student student4 = new Student(Colour.Yellow);
        Student student5 = new Student(Colour.Blue);
        Student student6 = new Student(Colour.Pink);
        Student student7 = new Student(Colour.Red);



        assertNull(island.Influence(arrayPlayers, false, -1)); //All'inizio non c'è nessun giocatore con influenza

        island.addStudent(student1);
        island.addStudent(student2);
        island.addStudent(student3);
        island.addStudent(student4);
        island.addStudent(student5);
        island.addStudent(student6);
        island.addStudent(student7);
        island.addStudent(student7);

        p1.getPlayerSchoolBoard().addProfessor(Colour.Green);
        p2.getPlayerSchoolBoard().addProfessor(Colour.Yellow);
        p2.getPlayerSchoolBoard().addProfessor(Colour.Blue);

        Player px =  island.Influence(arrayPlayers, false, -1);
        assertEquals(px,p2);
        assertEquals(island.Influence(arrayPlayers, false, -1),p2);

        assertEquals(arrayPlayers.size(),2);

        island.setNumTower(1);
        island.changeTowerColor(TowerColour.Black);

        assertNull(island.Influence(arrayPlayers, false, -1)); //parità
        island.setNumTower(2);
        assertEquals(island.Influence(arrayPlayers, false, -1),p1);
        island.addStudent(student5);
        island.addStudent(student5);
        assertEquals(island.Influence(arrayPlayers, false, -1),p2);
        assertEquals(island.getNumTower(),2);
        assertEquals(island.getIslandIndex(),5);
       ArrayList<Integer> array1 = island.getNumStudents();
       for(Integer i : array1) {
           System.out.println(i);
       }
    }
}
