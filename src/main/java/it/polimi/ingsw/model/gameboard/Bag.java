package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.player.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Bag implements Serializable {

    private ArrayList<Student> students;


    public Bag(){
        this.students = new ArrayList<>();
    }

    public void fill(ArrayList<Student> input) {

        for (Student s : input){
            this.students.add(s);
        }

        Collections.shuffle(students);

    }

    public Student draw(){
        if (students.size()>0) {
        Student s = students.get(0);
        students.remove(0);
        return s;
        }
        else return null;
    }

  /*  public ArrayList<Student> getStudents() {
        return students;
    } */

    public int getSize(){
        return students.size();
    }
}
