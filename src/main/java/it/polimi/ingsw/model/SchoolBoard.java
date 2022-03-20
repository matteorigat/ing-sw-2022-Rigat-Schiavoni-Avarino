package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Island;

import java.util.ArrayList;

public class SchoolBoard {
    private ArrayList<Student> studentsEntrance;   //studenti all'entrata: 9
    private DiningRoom diningRoom;
    private ArrayList<Professor> professors;
    private ArrayList<Tower> towers;

    public SchoolBoard(TowerColour towerColour) {
        this.studentsEntrance = new ArrayList<>();
        this.diningRoom = new DiningRoom();
        this.professors = new ArrayList<>(0);
        this.towers = new ArrayList<>(Parameters.numTowers);

        for (int i=0; i<Parameters.numTowers; i++) {
            Tower t = new Tower(towerColour);
            towers.add(t);
        }
    }

    public ArrayList<Student> getStudentsEntrance() {
        return studentsEntrance;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public TowerColour getTowerColor() {
       return towers.get(0).getTowerColor();
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
