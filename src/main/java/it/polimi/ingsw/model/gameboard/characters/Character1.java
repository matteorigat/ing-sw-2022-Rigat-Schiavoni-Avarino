package it.polimi.ingsw.model.gameboard.characters;

import it.polimi.ingsw.model.player.Student;
import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;
/** character card 1 effect : take 1 student from this card and place it on an island of your choice
 * .Then draw a new student from the bag and place it on this card*/

public class Character1 implements CharacterCard{
    private int index;
    private int cost;
    private ArrayList<Student> students;

    private static final String Description = "Prendi 1 studente e piazzalo su un'isola";

    /**
     * character1 constructor
     */
    public Character1() {
        this.index = 1;
        this.cost = 1;
        this.students = new ArrayList<>();
    }

    /**
     * getStudent method returns a student
     *
     * @param colorIndex
     * @return student
     */
    public Student getStudent(int colorIndex) {
        Student stud = null;
        for (Student s: students)
            if(s.getColour().equals(Colour.values()[colorIndex])){
                stud = s;
                students.remove(s);
                return stud;
            }

        return null;
    }

    /**
     * getStudents method returns a students
     * @return students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * checkColorExists method controls if exists the input colour parameter
     * @param colorIndex
     * @return
     */
    public boolean checkColorExists(int colorIndex){
        for (Student s: students)
            if(s.getColour().equals(Colour.values()[colorIndex]))
                return true;

        return false;
    }

    /**
     * addStudent method adds a student
     * @param s
     */
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
