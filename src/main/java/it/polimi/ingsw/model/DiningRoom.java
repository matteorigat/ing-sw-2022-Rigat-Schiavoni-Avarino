package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;

public class DiningRoom {

    private ArrayList<Student> greenStudents;
    private ArrayList<Student> redStudents;
    private ArrayList<Student> yellowStudents;
    private ArrayList<Student> pinkStudents;
    private ArrayList<Student> blueStudents;

    public DiningRoom() {
        this.greenStudents = new ArrayList<>(0);
        this.redStudents = new ArrayList<>(0);
        this.yellowStudents = new ArrayList<>(0);
        this.pinkStudents = new ArrayList<>(0);
        this.blueStudents = new ArrayList<>(0);
    }

    public void addStudent(Student s){                 // scusate i tanti if ma sono funzionali
        if(s.getColour().equals(Colour.Green))         // qui poi nelle regole esperte bisogna contare le  monete
            greenStudents.add(s);
        else if(s.getColour().equals(Colour.Red))
            redStudents.add(s);
        else if(s.getColour().equals(Colour.Yellow))
            yellowStudents.add(s);
        else if(s.getColour().equals(Colour.Pink))
            pinkStudents.add(s);
        else if(s.getColour().equals(Colour.Blue))
            blueStudents.add(s);
    }

    /*
    //variante con monete, se ritorna true il giocatore guadagna una moneta
    public boolean addStudent(Student s, int i){
        if(s.getColour().equals(Colour.Green)){
            greenStudents.add(s);
            if (greenStudents.size()%3 == 0) //moneta se 3, 6 e 9
                return true;
        }
        else if(s.getColour().equals(Colour.Red)){
            redStudents.add(s);
            if (redStudents.size()%3 == 0)
                return true;
        }
        else if(s.getColour().equals(Colour.Yellow)){
            yellowStudents.add(s);
            if (yellowStudents.size()%3 == 0)
                return true;
        }
        else if(s.getColour().equals(Colour.Pink)){
            pinkStudents.add(s);
            if (pinkStudents.size()%3 == 0)
                return true;
        }
        else if(s.getColour().equals(Colour.Blue)){
            blueStudents.add(s);
            if (blueStudents.size()%3 == 0)
                return true;
        }
        return false;
    }
     */

    public int numOfStudentByColor(Colour color){
        if(color.equals(Colour.Green)){
            return greenStudents.size();
        } else if(color.equals(Colour.Red)){
            return redStudents.size();
        } else if(color.equals(Colour.Yellow)){
            return yellowStudents.size();
        } else if(color.equals(Colour.Pink)){
            return pinkStudents.size();
        } else if(color.equals(Colour.Blue)){
            return blueStudents.size();
        }
        return -1;
    }


}
