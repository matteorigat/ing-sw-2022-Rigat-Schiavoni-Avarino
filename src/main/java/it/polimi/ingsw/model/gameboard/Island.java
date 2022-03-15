package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Professor;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;

import java.util.ArrayList;

import static it.polimi.ingsw.model.enumeration.Colour.Green;

public class Island {
    private int islandRank;
    private int numTower;
    private TowerColour towerColor;
    private ArrayList<Student> students;
    private ArrayList<Integer> numStudents;

    public void addStudent (Student s){
        this.students.add(s);

       // NumStudents[s.getColour().ordinal()]++ ;
       numStudents.set(s.getColour().ordinal(), (numStudents.get(s.getColour().ordinal()) + 1));
    }

    public Island(){
        numStudents = new ArrayList<>();
        for (int i = 0; i<5; i++){
            numStudents.add(0);
        }
    }

    public Player Influence (){

        ArrayList<Professor> prof = (ArrayList<Professor>) getProfessorsByTower(towerColor).clone();

        ArrayList<Integer> stud = (ArrayList<Integer>) numStudents.clone();

        for(Professor p: prof){
            stud.set(p.getProfessorColour().ordinal(), stud.get(p.getProfessorColour().ordinal() + numTower));
        }

        int num;
        int max = 0;
        for (int i = 0; i<5; i++){
            if(max < numStudents.get(i)) {
                max = numStudents.get(i);
                num = i;
            }
        }

        return getPlayerByProfessor(Colour.values()[num]);

    }





}
