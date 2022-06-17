package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.exceptions.TooManyStudentsOnCloudException;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.player.Student;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cloud Class defines 2 or 3 clouds (depending on the number of players) with 3 students on them.
 * At the end of every turn every player choose one of the cloud and get the students.
 * Then Clouds will be refilled.
 */
public class Cloud implements Serializable {

    private ArrayList<Student> students;
    private boolean taken;

    /**
     * Cloud constructor method
     */
    public Cloud() {
        this.students = new ArrayList<>(0);
        this.taken = false;
    }

    /**
     * addStudents method adds students on one cloud at the beginning of the turn
     * @param s
     * @throws TooManyStudentsOnCloudException
     */

    public void addStudent(Student s) throws TooManyStudentsOnCloudException {
        if (this.students.size() < Parameters.numCloudStudents){
            this.students.add(s);
        }
        else throw new TooManyStudentsOnCloudException();

    }

    /**
     * seeStudents method returns the arraylist of the students of the island
     * @return (ArrayList<Students>)
     */
    public ArrayList<Student> seeStudents() {
        return (ArrayList<Student>) students.clone();
    }



    /**
     * getStudents method get the arrayList of the students of the island
     * @return (ArrayList<Students>)
     */
    public ArrayList<Student> getStudents() {
        ArrayList<Student> stud = (ArrayList<Student>) students.clone();

        for(int i=0; i<stud.size(); i++)
            students.remove(0);

        this.taken = true;

        return stud;
    }

    /**
     * isTaken method returns a boolean which tells if the students on cloud are already taken
     * @return taken
     */
    public boolean isTaken() {
        return taken;
    }

    /**
     * setTaken method sets the boolean taken
     * @param taken
     */
    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
