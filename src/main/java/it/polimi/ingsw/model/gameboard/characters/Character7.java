package it.polimi.ingsw.model.gameboard.characters;

import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.player.Student;

import java.util.ArrayList;

/** Character card 7 effect : In setup draw 6 students and place them on this card.
 * You may take up to 3 students from this card and replace them with the same number of Students from your entrance*/
public class Character7 implements CharacterCard{
    private int index;
    private int cost;
    private ArrayList<Student> students;

    private static final String Description = "Prendi 3 studenti e scambiali con 3 del tuo ingresso";

    public Character7() {
        this.index = 7;
        this.cost = 1;
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

    /**
     * play method increases the cost of the card
     */
    @Override
    public void play() {
        this.cost++;
    }

    /**
     * getIndex method returns the index
     * @return index
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * getCost method returns the cost
     * @return cost
     */
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

    /**
     * getDescription method return the description
     * @return description
     */
    @Override
    public String getDescription(){
        return Description;
    }
}
