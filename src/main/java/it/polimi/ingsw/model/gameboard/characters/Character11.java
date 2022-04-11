package it.polimi.ingsw.model.gameboard.characters;

import it.polimi.ingsw.model.player.Student;
import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;

public class Character11 implements CharacterCard{
    private int index;
    private int cost;
    private ArrayList<Student> students;

    public Character11() {
        this.index = 11;
        this.cost = 2;
        this.students = new ArrayList<>();

    }

    public Student getStudent(int colorIndex) {
        Student stud = null;
        for (Student s: students)
            if(s.getColour().equals(Colour.values()[colorIndex]))
                stud = s;

        students.remove(stud);
        return stud; //non dovrebbe mai succedere
    }

    public boolean checkColorExists(int colorIndex){
        for (Student s: students)
            if(s.getColour().equals(Colour.values()[colorIndex]))
                return true;

        return false;
    }

    public void addStudent(Student s) {
        this.students.add(s);
    }

    @Override
    public void play() {
        this.cost++;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getCost() {
        return cost;
    }
}
