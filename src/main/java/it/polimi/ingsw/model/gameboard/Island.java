package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Professor;
import it.polimi.ingsw.model.player.Student;
import it.polimi.ingsw.model.enumeration.TowerColour;

import java.util.ArrayList;

public class Island {
   // private int islandRank;    //numero di isole unite
    //private int islandIndex;
    private int numTower;
    private TowerColour towerColor;
    private ArrayList<Student> students;
    private ArrayList<Integer> numStudents;
    private int noEntry; //int perchè ne puoi mettere più di uno


    public Island(){
        this.students = new ArrayList<Student>(0);
        //this.islandIndex = index;
        this.numTower = 0;
        //this.islandRank = 1;
        this.towerColor = null;
        this.numStudents = new ArrayList<>();

        if(Parameters.expertMode){
            this.noEntry = 0;
        }

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
    public Player Influence (ArrayList<Player> players, boolean noTowerFlag, int twoMorePointsPlayer){

        ArrayList<Professor> prof = new ArrayList<>();

        // sommo gli studenti (colori diversi) di un singolo giocatore in base ai colori che controlla
        int[] somma = new int[Parameters.numPlayers];
        for(Player pl: players) {
            prof = pl.getPlayerSchoolBoard().getProfessors();
            for (Professor pr : prof)
                somma[pl.getIndex()] += numStudents.get(pr.getProfessorColour().ordinal());

            if(pl.PlayerTowerColor().equals(towerColor) && !noTowerFlag)
                somma[pl.getIndex()] += numTower;

            if(pl.getIndex() == twoMorePointsPlayer)
                somma[pl.getIndex()] += 2;
        }

        ArrayList<Integer> rank = new ArrayList<>();
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
            for(Player p: players) {  //ritorno il giocatore che ha il max
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
      //  this.islandRank = numTower;
    }

    public TowerColour getTowerColor() {
        return towerColor;
    }

  /*  public int getIslandRank() {
        return islandRank;
    } */

   /* public int getIslandIndex() {
        return islandIndex;
    } */

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Integer> getNumStudents() {
        return numStudents;
    }

    public void addNoEntry() {
        this.noEntry++;
    }

    public void removeNoEntry(){
        this.noEntry--;
    }

    public int getNoEntry() {
        return noEntry;
    }
}
