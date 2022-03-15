package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Bag {

    ArrayList<Student> students;



    public void Bag(){
        students = new ArrayList<Student>();
    }
  public void fill(ArrayList<Student> input) {


       //   students.addAll(input);
          input.addAll(students);
          Collections.shuffle(students);
  }
  public Student draw(){
      Student s = students.get(0);
      students.remove(0);
      return s;
  }
}
