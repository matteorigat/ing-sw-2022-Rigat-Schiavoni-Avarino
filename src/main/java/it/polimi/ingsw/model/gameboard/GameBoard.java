package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.player.Student;
import it.polimi.ingsw.model.gameboard.characters.*;
import it.polimi.ingsw.model.gameboard.characters.CharacterCard;

import java.io.Serializable;
import java.util.ArrayList;
public class GameBoard implements Serializable {

    private CharacterDeck characterDeck;
    private ArrayList<Island> islands;
    private ArrayList<Cloud> clouds;
    private ArrayList<CharacterCard> threeCharacterCards;

    private int motherNature;
    private Bag bag;
    private int generalReserve;

    /**
     * GameBoard Constructor
     */
    public GameBoard() {
        this.islands = new ArrayList<>(Parameters.numIslands);
        this.clouds = new ArrayList<>(Parameters.numClouds);
        this.bag = new Bag();

        if(Parameters.expertMode){
            this.generalReserve = 20 - Parameters.numPlayers;
            this.characterDeck = new CharacterDeck();
        }


        for (int i=0; i<Parameters.numIslands; i++){
            Island isl = new Island();
            isl.setIslandIndex(i);
            islands.add(isl);
            if(i<Parameters.numClouds){
                Cloud cl = new Cloud();
                clouds.add(cl);
            }
        }
    }

    /**
     * addStudentOnIsland method adds one student on an island
     * @param numIsland
     * @param student
     */
    public void addStudentOnIsland(int numIsland, Student student){
        islands.get(numIsland).addStudent(student);
    }

    /**
     * chooseThreeCards method decides randomly the 3 character cards that will be playable in the game
     */
    public void chooseThreeCards(){
        threeCharacterCards = characterDeck.getThreeRandomCards();

        for(CharacterCard c: threeCharacterCards){
            if(c.getIndex() == 1){
                for (int i=0; i<4; i++)
                    ((Character1) c).addStudent(getBag().draw());
            } else if(c.getIndex() == 7){
                for (int i=0; i<6; i++)
                    ((Character7) c).addStudent(getBag().draw());
            } else if(c.getIndex() == 11){
                for (int i=0; i<4; i++)
                    ((Character11) c).addStudent(getBag().draw());
            }
        }
    }

    /**
     * getThreeCharacterCards method returns the 3 character cards in current game
     * @return threeCharacterCards
     */

    public ArrayList<CharacterCard> getThreeCharacterCards() {
        return threeCharacterCards;
    }

    public void addStudentOnCloud(int numCloud, Student student){
        clouds.get(numCloud).addStudent(student);
    }

    /**
     * getMoteherNature method returns the position of MotherNature (which Island she's currenly on).
     * @return motherNature
     */
    public int getMotherNature() {
        return motherNature;
    }


    /**
     * setMOtherNature method sets motherNature value
     * @param motherNature
     */
    public void setMotherNature(int motherNature) {
        this.motherNature = motherNature;
    }

    /**
     * getBag Method return the Bag
     * @return Bag
     */
    public Bag getBag() {
        return bag;
    }

    /**
     * getClouds method returns the arrayList of Clouds
     * @return clouds
     */
    public ArrayList<Cloud> getClouds() {
        return clouds;
    }

    /**
     * IslandFusion method permits to do the merge of 2 islands.
     * @param island1
     * @param island2
     */
    public void islandFusion(int island1, int island2) {

        for(Student s: islands.get(island2).getStudents())
            islands.get(island1).addStudent(s);

        this.islands.get(island1).setNumTower(this.islands.get(island1).getNumTower() + this.islands.get(island2).getNumTower());


        islands.remove(island2);

    }

    /**
     * getOneCoin method get one coin and remove it from the general reserve
     * @return Boolean
     */
    public boolean getOneCoin(){
        if(this.generalReserve > 0){
            this.generalReserve--;
            return true;
        } else
            return false;
    }

    /**
     * getGeneralReserve method returns how many coins are left in the general reserve
     * @return generalResevre
     */
    public int getGeneralReserve() {
        return generalReserve;
    }

    /**
     * addCoinsToGeneralReserve adds a coin back to the general reserve
     * @param coins
     */
    public void addCoinsToGeneralReserve(int coins) {
        this.generalReserve += coins;
    }

    /**
     * getIsland method returns the arrayList of the islands
     * @return islands
     */
    public ArrayList<Island> getIslands() {
        return islands;
    }
}
