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

       // NumStudents[s.getColour().ordinal()]++  ;
       numStudents.set(s.getColour().ordinal(), (numStudents.get(s.getColour().ordinal()) + 1));
    }

    //Return the player with the most influence on the island
    public Player Influence (ArrayList<Player> players){

        ArrayList<Professor> prof = new ArrayList<>();

        for(Player p: players)    //prendo i professori del giocatore che controlla l'isola
            if(p.PlayerTowerColor().equals(towerColor))
                prof = p.getPlayerSchoolBoard().getProfessors();

        ArrayList<Integer> stud = (ArrayList<Integer>) numStudents.clone();  //clone fa la copia dell'array

        for(Professor p: prof){   //per ogni colore di un professore di chi domina sommo numStudent e il numero di torri
            stud.set(p.getProfessorColour().ordinal(), numStudents.get(p.getProfessorColour().ordinal()) + numTower);
        }

        int[] somma = new int[Parameters.numPlayers];
        for(Player pl: players) {   // sommo gli studenti (colori diversi) di un singolo giocatore
            prof = pl.getPlayerSchoolBoard().getProfessors();
            for (Professor pr : prof)
                somma[pl.getIndex()] += stud.get(pr.getProfessorColour().ordinal());
        }

        ArrayList<Integer> rank = null;
        int max = 0;
        for (int i = 0; i<Parameters.numPlayers; i++){  //trovo il giocatore con più studenti
            if(max < somma[i]){
                max = somma[i];
                rank.clear();
                rank.add(i);
            }else if (max == stud.get(i) && max != 0){  //qui ho parità
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
