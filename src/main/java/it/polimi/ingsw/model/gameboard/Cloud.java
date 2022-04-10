package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.exceptions.TooManyStudentsOnCloudException;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.player.Student;

import java.util.ArrayList;

public class Cloud {

    private ArrayList<Student> students;
    private boolean taken;

    public Cloud() {
        this.students = new ArrayList<>(0);
        this.taken = false;
    }

    // A inizio turmo si aggiungono giocatori sulla nuvola... è possibile modificare la funzione e passare tutti gli studenti insieme come arraylist
    public void addStudent(Student s) throws TooManyStudentsOnCloudException {
        if (this.students.size() < Parameters.numCloudStudents){
            this.students.add(s);
        }
        else throw new TooManyStudentsOnCloudException();

    }

    public ArrayList<Student> seeStudents() {
        return (ArrayList<Student>) students.clone();
    }

    //quando un giocatore finisce il turno, prende gli studenti dalla nuvola e la svuota
    public ArrayList<Student> getStudents() {
        ArrayList<Student> stud = (ArrayList<Student>) students.clone();

        for(int i=0; i<stud.size(); i++)
            students.remove(0);

        this.taken = true;

        return stud;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
