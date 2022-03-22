package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Professor;
import it.polimi.ingsw.model.Student;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;

import java.util.ArrayList;

import static it.polimi.ingsw.model.enumeration.Colour.Green;

public class Island {
    private int islandRank;
    private int islandIndex;
    private int numTower;
    private TowerColour towerColor;
    private ArrayList<Student> students;
    private ArrayList<Integer> numStudents;

    public void setNumTower(int numTower) {
        this.numTower = numTower;
    }
    public void addTower(TowerColour colour){
        this.numTower = this.islandRank;
        this.towerColor = colour;
    }
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

        for(Professor p: prof){   //per ogni colore di un professore sommo al colore di numStudent corrispondente il numero di torri
            stud.set(p.getProfessorColour().ordinal(), numStudents.get(p.getProfessorColour().ordinal()) + numTower);
        }

        int num = 6;  //dovevo inizializzarlo per non avere errore, 6 non rappresenta nessun colore
        int max = 0;
        for (int i = 0; i<5; i++){   //trovo il colore del massimo tra studenti o studenti+professori
            if(max < stud.get(i)) {
                max = stud.get(i);
                num = i;
            }
        }

        for(Player p: players) {  //trovo a quale giocatore controlla il professore del colore max
            prof = p.getPlayerSchoolBoard().getProfessors();
            for (Professor pr : prof)
                if (pr.getProfessorColour().equals(Colour.values()[num]))
                    return p;
        }

        return null;  // se non trova chi domina l'isola ritorna null
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
