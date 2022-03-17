package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Island;

import java.util.ArrayList;

public class SchoolBoard {
    private ArrayList<Student> studentsEntrance;   // studenti all'entrata: 9
    private DiningRoom diningRoom;
    private ArrayList<Professor> professors;
    private ArrayList<Tower> tower;

    public SchoolBoard() {
        this.studentsEntrance = new ArrayList<>();
        this.diningRoom = new DiningRoom();
        this.professors = new ArrayList<>(0);
        this.tower = new ArrayList<>();
    }

    public ArrayList<Student> getStudentsEntrance() {
        return studentsEntrance;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public TowerColour getTowerColor() {
       return tower.get(0).getTowerColor();
    }

    public void moveStudentToDiningRoom(Student s){
        diningRoom.addStudent(s);
        this.studentsEntrance.remove(s);
    }

    public void moveStudentToIsland(Student s, Island i){
        i.addStudent(s);
        this.studentsEntrance.remove(s);
    }
}
