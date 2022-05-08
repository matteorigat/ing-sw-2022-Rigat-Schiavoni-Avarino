package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.Colour;

import java.io.Serializable;
import java.util.ArrayList;

public class DiningRoom implements Serializable {

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
        if(s.getColour().equals(Colour.Green) && greenStudents.size() < 10){
            greenStudents.add(s);
            if (greenStudents.size()%3 == 0) //moneta se 3, 6 e 9
                return true;
        }
        else if(s.getColour().equals(Colour.Red) && redStudents.size() < 10){
            redStudents.add(s);
            if (redStudents.size()%3 == 0)
                return true;
        }
        else if(s.getColour().equals(Colour.Yellow) && yellowStudents.size() < 10){
            yellowStudents.add(s);
            if (yellowStudents.size()%3 == 0)
                return true;
        }
        else if(s.getColour().equals(Colour.Pink) && pinkStudents.size() < 10){
            pinkStudents.add(s);
            if (pinkStudents.size()%3 == 0)
                return true;
        }
        else if(s.getColour().equals(Colour.Blue) && blueStudents.size() < 10){
            blueStudents.add(s);
            if (blueStudents.size()%3 == 0)
                return true;
        }
        return false;
    }


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
        return -1;//non dovrebbe mai succedere
    }

    public ArrayList<Student> removeThreeStudents(Colour color){
        ArrayList<Student> stud = new ArrayList<>();
        int num = 3;
        if(numOfStudentByColor(color) < 3)
            num = numOfStudentByColor(color);

        for (int i=0; i<num; i++){
            if(color.equals(Colour.Green)){
                stud.add(greenStudents.get(0));
                greenStudents.remove(0);
            } else if(color.equals(Colour.Red)){
                stud.add(redStudents.get(0));
                redStudents.remove(0);
            } else if(color.equals(Colour.Yellow)){
                stud.add(yellowStudents.get(0));
                yellowStudents.remove(0);
            } else if(color.equals(Colour.Pink)){
                stud.add(pinkStudents.get(0));
                pinkStudents.remove(0);
            } else if(color.equals(Colour.Blue)){
                stud.add(blueStudents.get(0));
                blueStudents.remove(0);
            }
        }

        return stud;
    }

    public void removeOneStudent(int colorIndex){
        if(colorIndex == 0){
            greenStudents.remove(0);
        } else if(colorIndex == 1){
            redStudents.remove(0);
        } else if(colorIndex == 2){
            yellowStudents.remove(0);
        } else if(colorIndex == 3){
            pinkStudents.remove(0);
        } else if(colorIndex == 4){
            blueStudents.remove(0);
        }
    }
}
