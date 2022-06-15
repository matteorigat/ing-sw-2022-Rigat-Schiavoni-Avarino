package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.player.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Bag Class is where every student instance is created.
 */
public class Bag implements Serializable {

    private ArrayList<Student> students;

    /**
     * Bag constructor
     */
    public Bag(){
        this.students = new ArrayList<>();
    }

    /**
     * fill method fills the Bag and then shuffles the students
     * @param input
     */
    public void fill(ArrayList<Student> input) {

        for (Student s : input)
            this.students.add(s);

        Collections.shuffle(students);

    }

    /**
     * draw method let us draw a student from the bag
     * @return s student
     */
    public Student draw(){
        if (students.size()>0){
           Student s = students.get(0);
           students.remove(0);
           return s;
        }
        else return null;
    }


    /**
     * getSize method returns how many students are left in the Bag
     * @return students left
     */
    public int getSize(){
        return students.size();
    }
}
