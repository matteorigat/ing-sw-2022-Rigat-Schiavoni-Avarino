package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.TowerColour;

import java.util.ArrayList;

public class SchoolBoard {
    private ArrayList<Student> students;
    private ArrayList<Professor> professors;
    private ArrayList<Tower> tower;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public TowerColour getTowerColor() {
       return tower.get(0).getTowerColor();
    }
}
