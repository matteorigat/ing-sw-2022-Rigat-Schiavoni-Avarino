package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Professor;
import it.polimi.ingsw.model.player.Student;
import it.polimi.ingsw.model.enumeration.TowerColour;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Island Class defines every 12 islands where the game takes place.
 */
public class Island implements Serializable {

    private int islandIndex;
    private int numTower;
    private TowerColour towerColor;
    private ArrayList<Student> students;
    private ArrayList<Integer> numStudents;
    private int noEntry; //int perchè ne puoi mettere più di uno


    /**
     * Island constructor
     */
    public Island(){
        this.students = new ArrayList<Student>(0);
        this.numTower = 0;
        this.towerColor = null;
        this.numStudents = new ArrayList<>();

        if(Parameters.expertMode){
            this.noEntry = 0;
        }

        for (int i = 0; i<5; i++){
            numStudents.add(0);
        }
    }

    /**
     * changeTowerColour methods permits us to change tower colour when one player has more
     * influnce than the owner of tower on the island
     * @param colour
     */
    public void changeTowerColor(TowerColour colour){
        this.towerColor = colour;
    }

    /**
     * addStudent method adds a student and update the number of the students on the island
     * @param s
     */
    public void addStudent (Student s){
        this.students.add(s);

        numStudents.set(s.getColour().ordinal(), (numStudents.get(s.getColour().ordinal()) + 1));
    }

    /**
     *  Influence method returns the player with the most influence on the island
     * @param players
     * @param card6noTowerFlag
     * @param card8twoMorePointsPlayer
     * @param card9color
     * @return Player
     */

    public Player Influence (ArrayList<Player> players, boolean card6noTowerFlag, int card8twoMorePointsPlayer, int card9color){



        // sommo gli studenti (colori diversi) di un singolo giocatore in base ai colori che controlla
        int[] somma = new int[Parameters.numPlayers];
        for(Player pl: players) {

            for (Professor pr : pl.getPlayerSchoolBoard().getProfessors())
                if(pr.getProfessorColour().ordinal() != card9color)
                    somma[pl.getIndex()] += numStudents.get(pr.getProfessorColour().ordinal());

            if(pl.PlayerTowerColor().equals(towerColor) && !card6noTowerFlag)
                somma[pl.getIndex()] += numTower;

            if(pl.getIndex() == card8twoMorePointsPlayer)
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

    /**
     * getNumTower method returns the number of the towers on the island
     * @return numTower
     */
    public int getNumTower() {
        return numTower;
    }

    /**
     * SetNUmTower method sets the number of tower
     * @param numTower
     */
    public void setNumTower(int numTower) {
        this.numTower = numTower;
      //  this.islandRank = numTower;
    }

    /**
     * getTowerColour method returns the colour of the towers on the island
     * @return TowerColour
     */
    public TowerColour getTowerColor() {
        return towerColor;
    }


    /**
     * getStudents method returns the students on the island
     * @return students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * getNumStudents method returns the number of the Students on the island
     * @return numTower
     */
    public ArrayList<Integer> getNumStudents() {
        return numStudents;
    }

    public void addNoEntry() {
        this.noEntry++;
    }


    public void removeNoEntry(){
        this.noEntry--;
    }

    /**
     * getNoEntry method returns noEntry
     * @return NoEntry
     */
    public int getNoEntry() {
        return noEntry;
    }

    /**
     * getIslandIndex method returns the index of the island
     * @return IslandIndex
     */
    public int getIslandIndex() {
        return islandIndex;
    }

    /**
     * setIslandIndex method set the index
     * @return IslandIndex
     */
    public void setIslandIndex(int islandIndex) {
        this.islandIndex = islandIndex;
    }
}
