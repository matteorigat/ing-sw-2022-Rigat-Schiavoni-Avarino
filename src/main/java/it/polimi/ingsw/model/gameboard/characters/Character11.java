package it.polimi.ingsw.model.gameboard.characters;

import it.polimi.ingsw.model.player.Student;
import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;

public class Character11 implements CharacterCard{
    private int index;
    private int cost;
    private ArrayList<Student> students;

    private static final String Description = "Prendi 1 studente e piazzalo nella sala";
    public Character11() {
        this.index = 11;
        this.cost = 2;
        this.students = new ArrayList<>();

    }

    public Student getStudent(int colorIndex) {
        Student stud = null;
        for (Student s: students)
            if(s.getColour().equals(Colour.values()[colorIndex])){
                stud = s;
                students.remove(s);
                return stud;
            }

        return null; //non dovrebbe mai succedere
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

    @Override
    public String toString() {
        return  "index: " + index +
                "\tcost: " + cost +
                "\t\tstudents: " + students +  "\t\t" + Description;
    }
    @Override
    public String getDescription(){
        return Description;
    }
}
