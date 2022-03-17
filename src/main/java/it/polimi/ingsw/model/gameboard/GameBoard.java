package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;
import java.util.LinkedList;
public class GameBoard {

    private LinkedList<Island> islands;
    private ArrayList<Cloud> clouds;
    private MotherNature motherNature;
    private Bag bag;


    public void islandFusion(int island1, int island2) {

        for(Student s: islands.get(island2).getStudents())
            islands.get(island1).addStudent(s);

        islands.remove(island2);

    }

    public void islandFusion(int island1, int island2, int island3) {  //se conquisto l'isola in mezzo a due già mie, ho una fusione a tre

        for(Student s: islands.get(island2).getStudents())
            islands.get(island1).addStudent(s);

        for(Student s: islands.get(island3).getStudents())
            islands.get(island1).addStudent(s);

        islands.remove(island2);
        islands.remove(island3);
    }

    public void addFirstStudentOnIsland(){
        for (Island i: islands){
            if(0 == 0) {  //sostituire con se non c'è madrenatura su i e se i è isola opposta a madrenatura (i + 6)
                i.addStudent(bag.draw());
            }

        }
    }

}
