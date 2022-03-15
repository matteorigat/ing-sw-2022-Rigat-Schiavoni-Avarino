package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;

import static it.polimi.ingsw.model.enumeration.Colour.Green;

public class Island {
    private int IslandRank;
    private int Tower;
    private ArrayList<Student> students;
    private ArrayList<Integer> NumStudents;

    public void addStudent (Student s){
        this.students.add(s);

       // NumStudents[s.getColour().ordinal()]++ ;
       NumStudents.set(s.getColour().ordinal(), (NumStudents.get(s.getColour().ordinal()) + 1);
    }

    public Island(){
        NumStudents = new ArrayList<>();
        for (int i = 0; i<5; i++){
            NumStudents.add(0);
        }
    }
    public Player influence (){
        for (int i=0; i<5; i++){

        }
    }





}
