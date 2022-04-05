package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.Colour;

public class DiningRoom {

    private int greenStudents;
    private int redStudents;
    private int yellowStudents;
    private int pinkStudents;
    private int blueStudents;

    public DiningRoom() {
        this.greenStudents = 0;
        this.redStudents = 0;
        this.yellowStudents = 0;
        this.pinkStudents = 0;
        this.blueStudents = 0;
    }
    /*
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

     */


    //variante con monete, se ritorna true il giocatore guadagna una moneta
    public boolean addStudent(Student s){
        if(s.getColour().equals(Colour.Green) && greenStudents < 10){
            greenStudents++;
            if (greenStudents%3 == 0) //moneta se 3, 6 e 9
                return true;
        }
        else if(s.getColour().equals(Colour.Red) && redStudents < 10){
            redStudents++;
            if (redStudents%3 == 0)
                return true;
        }
        else if(s.getColour().equals(Colour.Yellow) && yellowStudents < 10){
            yellowStudents++;
            if (yellowStudents%3 == 0)
                return true;
        }
        else if(s.getColour().equals(Colour.Pink) && pinkStudents < 10){
            pinkStudents++;
            if (pinkStudents%3 == 0)
                return true;
        }
        else if(s.getColour().equals(Colour.Blue) && blueStudents < 10){
            blueStudents++;
            if (blueStudents%3 == 0)
                return true;
        }
        return false;
    }


    public int numOfStudentByColor(Colour color){
        if(color.equals(Colour.Green)){
            return greenStudents;
        } else if(color.equals(Colour.Red)){
            return redStudents;
        } else if(color.equals(Colour.Yellow)){
            return yellowStudents;
        } else if(color.equals(Colour.Pink)){
            return pinkStudents;
        } else if(color.equals(Colour.Blue)){
            return blueStudents;
        }
        return -1;//non dovrebbe mai succedere
    }


}
