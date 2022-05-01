package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Island;

import java.io.Serializable;
import java.util.ArrayList;

public class SchoolBoard implements Serializable {
    private ArrayList<Student> studentsEntrance;   //studenti all'entrata: 9
    private DiningRoom diningRoom;
    private ArrayList<Professor> professors;
    private ArrayList<Tower> towers;
    private TowerColour towerColor;

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

    public void addTower(TowerColour towerColour){
        Tower t = new Tower(towerColour);
        towers.add(t);
    }

    public boolean moveStudentToDiningRoom(int studentColour) {
        if(this.diningRoom.numOfStudentByColor(Colour.values()[studentColour]) >= 10)
            return false;  //sala piena, non gli faccio aggiungere nulla

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

    public void removeProfessor(Colour color){
        int size = professors.size();
        for(int i=0; i<size; i++){
            if(professors.get(i).getProfessorColour().equals(color)){
                professors.remove(i);
                return;
            }
        }
    }

    public void addProfessor(Colour color){
        Professor pr = new Professor(color);
        professors.add(pr);
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }
}
