package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Student;

import java.util.ArrayList;
import java.util.Collection;

public class Cloud {

    private ArrayList<Student> students;

    public Cloud() {
        this.students = new ArrayList<>(0);
    }

    // A inizio turmo si aggiungono giocatori sulla nuvola... è possibile modificare la funzione e passare tutti gli studenti insieme come arraylist
    public void addStudent(Student s) {
        this.students.add(s);
    }

    //quando un giocatore finisce il turno, prende gli studenti dalla nuvola e la svuota
    public ArrayList<Student> getStudents() {
        ArrayList<Student> stud = (ArrayList<Student>) students.clone();

        for(Student s: students)
            students.remove(s);

        return stud;
    }

}
