package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.Professor;
import it.polimi.ingsw.model.player.Student;
import it.polimi.ingsw.model.enumeration.TowerColour;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Island Class defines every island where the game takes place.
 */
public class Island implements Serializable {

    private int islandIndex;
    private int numTower;
    private TowerColour towerColor;
    private ArrayList<Student> students;
    private ArrayList<Integer> numStudents;
    private int noEntry;


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
     * changeTowerColour methods permits to change the tower's colour when one player has
     * more influence than the current owner of the Island.
     * @param colour
     */
    public void changeTowerColor(TowerColour colour){
        this.towerColor = colour;
    }

    /**
     * addStudent method adds a student on the Island and updates the number of the students on the Island
     * @param s
     */
    public void addStudent (Student s){
        this.students.add(s);

        numStudents.set(s.getColour().ordinal(), (numStudents.get(s.getColour().ordinal()) + 1));
    }

    /**
     *  Influence method returns the player with the most influence on the Island.
     * @param players
     * @param card6noTowerFlag
     * @param card8twoMorePointsPlayer
     * @param card9color
     * @return Player
     */

    public Player Influence (ArrayList<Player> players, boolean card6noTowerFlag, int card8twoMorePointsPlayer, int card9color){

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
        for (int i = 0; i<Parameters.numPlayers; i++){
            if(max < somma[i]){
                max = somma[i];
                rank.clear();
                rank.add(i);
            }else if (max == somma[i] && max != 0){
                rank.add(i);
            }
        }

        if(rank.size() == 1){
            for(Player p: players) {
               if(p.getIndex() == rank.get(0))
                   return p;
            }
        }

        return null;
    }

    /**
     * getNumTower method returns the number of Towers on the Island
     * @return numTower
     */
    public int getNumTower() {
        return numTower;
    }

    /**
     * SetNumTower method sets the number of Towers
     * @param numTower
     */
    public void setNumTower(int numTower) {
        this.numTower = numTower;

    }

    /**
     * getTowerColour method returns the colour of the Towers on the Island
     * @return TowerColour
     */
    public TowerColour getTowerColor() {
        return towerColor;
    }


    /**
     * getStudents method returns the students on the Island
     * @return students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * getNumStudents method returns the number of the students on the Island
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
     * getNoEntry method returns the number of noEntry tiles
     * @return NoEntry
     */
    public int getNoEntry() {
        return noEntry;
    }

    /**
     * getIslandIndex method returns the index of the Island
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
