package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Island;

import java.io.Serializable;
import java.util.ArrayList;

/** SchoolBoard Class it's composed by three arraylist which are professors,towers and students entrance and a dining room */
public class SchoolBoard implements Serializable {
    private ArrayList<Student> studentsEntrance;
    private DiningRoom diningRoom;
    private ArrayList<Professor> professors;
    private ArrayList<Tower> towers;
    private TowerColour towerColor;



    /** SchoolBoard constructor */
    public SchoolBoard(TowerColour towerColour) {
        this.studentsEntrance = new ArrayList<>();
        this.diningRoom = new DiningRoom();
        this.professors = new ArrayList<>(0);
        this.towers = new ArrayList<>(Parameters.numTowers);
        this.towerColor = towerColour;

        for (int i=0; i<Parameters.numTowers; i++) {
            Tower t = new Tower(towerColour);
            towers.add(t);
        }
    }

    public ArrayList<Student> getStudentsEntrance() {
        return studentsEntrance;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public TowerColour getTowerColor() {
       return towerColor;
    }

    /** this method adds a tower */
    public void addTower(TowerColour towerColour){
        Tower t = new Tower(towerColour);
        towers.add(t);
    }


    /**this method add a student in to the dining room and of course remove it from the students entrance
     * it also checks if the room is full (in case it doesn't let you add anything)  */
    public boolean moveStudentToDiningRoom(int studentColour) {
        if(this.diningRoom.numOfStudentByColor(Colour.values()[studentColour]) >= 10)
            return false;

        boolean coin = false;
        for (Student s : studentsEntrance){
            if (s.getColour().equals(Colour.values()[studentColour])) {
                coin = this.diningRoom.addStudent(s);
                studentsEntrance.remove(s);
                break;
            }
        }
        return coin;
    }

    /**
     * this method remove a student from entrance by his colour
     * @param studentColour
     */
    public void removeStudentFromEntrance(int studentColour) {
        for (Student s : studentsEntrance) {
            if (s.getColour().equals(Colour.values()[studentColour])) {
                this.studentsEntrance.remove(s);
                return;
            }
        }
    }

    /** this method allows you to move a student from the students entrance to an island */
    public void moveStudentToIsland(int studentColour, Island i) {
        for (Student s : studentsEntrance) {
            if (s.getColour().equals(Colour.values()[studentColour])) {

                i.addStudent(s);
                this.studentsEntrance.remove(s);
                break;
            }
        }
    }

    public DiningRoom getDiningRoom() {
        return diningRoom;
    }

    /** this method removes a professor from a player's gameboard */
    public void removeProfessor(Colour color){
        int size = professors.size();
        for(int i=0; i<size; i++){
            if(professors.get(i).getProfessorColour().equals(color)){
                professors.remove(i);
                return;
            }
        }
    }

    /**
     * this method adds a professor by the colour
     * @param color
     */
    public void addProfessor(Colour color){
        Professor pr = new Professor(color);
        professors.add(pr);
    }

    /**
     * Method getTowers returns the every towers of the same type.
     * @return towers
     */
    public ArrayList<Tower> getTowers() {
        return towers;
    }
}
