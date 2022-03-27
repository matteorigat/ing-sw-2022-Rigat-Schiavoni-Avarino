package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Professor;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;

import java.util.ArrayList;

import static it.polimi.ingsw.model.enumeration.Colour.Green;

public class Island {
    private int islandRank;    //numero di isole unite
    private int islandIndex;
    private int numTower;
    private TowerColour towerColor;
    private ArrayList<Student> students;
    private ArrayList<Integer> numStudents;


    public Island(){
        this.students = new ArrayList<Student>(0);
        this.numTower = 0;
        this.islandRank = 1;
        this.towerColor = null;
        this.numStudents = new ArrayList<>();

        for (int i = 0; i<5; i++){
            numStudents.add(0);
        }
    }

    public void changeTowerColor(TowerColour colour){
        this.towerColor = colour;
    }

    public void addStudent (Student s){
        this.students.add(s);

        numStudents.set(s.getColour().ordinal(), (numStudents.get(s.getColour().ordinal()) + 1));
    }

    //Return the player with the most influence on the island
    public Player Influence (ArrayList<Player> players){

        ArrayList<Professor> prof = new ArrayList<>();

        // sommo gli studenti (colori diversi) di un singolo giocatore in base ai colori che controlla
        int[] somma = new int[Parameters.numPlayers];
        for(Player pl: players) {
            prof = pl.getPlayerSchoolBoard().getProfessors();
            for (Professor pr : prof)
                somma[pl.getIndex()] += numStudents.get(pr.getProfessorColour().ordinal());

            if(pl.PlayerTowerColor().equals(towerColor))
                somma[pl.getIndex()] += numTower;
        }

        ArrayList<Integer> rank = null;
        int max = 0;
        for (int i = 0; i<Parameters.numPlayers; i++){  //trovo il giocatore con più studenti
            if(max < somma[i]){
                max = somma[i];
                rank.clear();
                rank.add(i);
            }else if (max == somma[i] && max != 0){  //qui ho parità
                rank.add(i);
            }
        }

        if(rank.size() == 1){
            for(Player p: players) {  //ritoeno il giocatore che ha il max
               if(p.getIndex() == rank.get(0))
                   return p;
            }
        }

        return null;  // se non trova nessun player o più di uno
    }

    public int getNumTower() {
        return numTower;
    }

    public void setNumTower(int numTower) {
        this.numTower = numTower;
    }

    public TowerColour getTowerColor() {
        return towerColor;
    }

    public int getIslandRank() {
        return islandRank;
    }

    public void setIslandRank(int islandRank) {
        this.islandRank = islandRank;
    }

    public int getIslandIndex() {
        return islandIndex;
    }

    public void setIslandIndex(int islandIndex) {
        this.islandIndex = islandIndex;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
