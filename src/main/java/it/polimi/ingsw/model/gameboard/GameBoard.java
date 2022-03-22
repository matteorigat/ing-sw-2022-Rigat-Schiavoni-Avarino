package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.AssistantCard;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;

import java.util.ArrayList;
public class GameBoard {

    private ArrayList<Island> islands;
    private ArrayList<Cloud> clouds;

    private int motherNature;
    private Bag bag;

    public GameBoard() {
        this.islands = new ArrayList<>(Parameters.numIsland);
        this.clouds = new ArrayList<>(Parameters.numClouds);
        this.bag = new Bag();

        for (int i=0; i<Parameters.numIsland; i++){
            Island isl = new Island();
            islands.add(isl);
        }

        for (int i=0; i<Parameters.numClouds; i++){
            Cloud cl = new Cloud();
            clouds.add(cl);
        }
    }

    public void addStudentOnIsland(int numIsland, Student student){
        islands.get(numIsland).addStudent(student);
    }

    public void addStudentOnCloud(int numCloud, Student student){
        clouds.get(numCloud).addStudent(student);
    }

    public int getMotherNature() {
        return motherNature;
    }


    public void setMotherNature(int motherNature) {
        this.motherNature = motherNature;
    }

    public Bag getBag() {
        return bag;
    }

    public ArrayList<Cloud> getClouds() {
        return clouds;
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

    public ArrayList<Island> getIslands() {
        return islands;
    }
}
