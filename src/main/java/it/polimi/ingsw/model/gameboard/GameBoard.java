package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;
public class GameBoard {

    private ArrayList<Island> islands;
    private ArrayList<Cloud> clouds;
    private int motherNature;
    private Bag bag;

    public GameBoard() {
        this.islands = new ArrayList<>(12);
        this.clouds = new ArrayList<>(); //  mettere una define globale per la grandezza
        this.bag = new Bag();
    }

    public void addFirstStudentOnIsland(int mn){
        for (Island i: islands){
            if(i.getIslandRank() != mn || i.getIslandRank() != mn + 1) {  //sostituire con se non c'è madrenatura su i e se i non è isola opposta a madrenatura (i + 6)
                i.addStudent(bag.draw());
            }
        }
    }

    public int getMotherNature() {
        return motherNature;
    }


    public void setMotherNature(int motherNature) {
        this.motherNature = motherNature;
    }

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
}
