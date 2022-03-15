package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;

import static it.polimi.ingsw.model.enumeration.Colour.Green;

public class Island {
    private int IslandRank;
    private int Tower;
    private ArrayList<Student> students;
    private int[] NumStudents;

    public void addStudent (Student s){
        this.students.add(s);
        NumStudents[Colour.valueOf(s.getColour().toString())]++ ;

    }




}
