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
    private int numTower;
    private TowerColour towerColor;
    private ArrayList<Student> students;
    private ArrayList<Integer> numStudents;


    public void addStudent (Student s){
        this.students.add(s);

       // NumStudents[s.getColour().ordinal()]++ ;
       numStudents.set(s.getColour().ordinal(), (numStudents.get(s.getColour().ordinal()) + 1));
    }

    public Island(){
        numStudents = new ArrayList<>();
        for (int i = 0; i<5; i++){
            numStudents.add(0);
        }
    }

    //Return the player with the most influence on the island
    public Player Influence (ArrayList<Player> players){

        ArrayList<Professor> prof = new ArrayList<>();

        for(Player p: players)
            if(p.PlayerTowerColor().equals(towerColor))
                prof = p.getPlayerSchoolBoard().getProfessors();

        ArrayList<Integer> stud = (ArrayList<Integer>) numStudents.clone();

        for(Professor p: prof){
            stud.set(p.getProfessorColour().ordinal(), stud.get(p.getProfessorColour().ordinal()) + numTower);
        }

        int num = 6;  //dovevo inizializzarlo per non avere errore, 6 non rappresenta nessun colore
        int max = 0;
        for (int i = 0; i<5; i++){
            if(max < stud.get(i)) {
                max = stud.get(i);
                num = i;
            }
        }

        for(Player p: players) {
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

    public ArrayList<Student> getStudents() {
        return students;
    }
}
