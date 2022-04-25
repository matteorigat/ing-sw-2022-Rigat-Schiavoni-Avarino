package it.polimi.ingsw.model.gameboard;

import it.polimi.ingsw.exceptions.TooManyStudentsOnCloudException;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.player.Student;
import it.polimi.ingsw.model.gameboard.characters.*;
import it.polimi.ingsw.model.gameboard.characters.CharacterCard;

import java.util.ArrayList;
public class GameBoard {

    private CharacterDeck characterDeck;
    private ArrayList<Island> islands;
    private ArrayList<Cloud> clouds;
    private ArrayList<CharacterCard> threeCharacterCards;

    private int motherNature;
    private Bag bag;
    private int generalReserve;

    public GameBoard() {
        this.islands = new ArrayList<>(Parameters.numIslands);
        this.clouds = new ArrayList<>(Parameters.numClouds);
        this.bag = new Bag();

        if(Parameters.expertMode){
            this.generalReserve = 20 - Parameters.numPlayers;
            this.characterDeck = new CharacterDeck();
        }


        for (int i=0; i<Parameters.numIslands; i++){
            Island isl = new Island(i);
            islands.add(isl);
            if(i<Parameters.numClouds){
                Cloud cl = new Cloud();
                clouds.add(cl);
            }
        }
    }

    public void addStudentOnIsland(int numIsland, Student student){
        islands.get(numIsland).addStudent(student);
    }

    public void chooseThreeCards(){
        threeCharacterCards = characterDeck.getThreeRandomCards();

        for(CharacterCard c: threeCharacterCards){
            if(c.getIndex() == 1){
                for (int i=0; i<4; i++)
                    ((Character1) c).addStudent(getBag().draw());
            } else if(c.getIndex() == 11){
                for (int i=0; i<4; i++)
                    ((Character11) c).addStudent(getBag().draw());
            }
        }
    }

    public ArrayList<CharacterCard> getThreeCharacterCards() {
        return threeCharacterCards;
    }

    public void addStudentOnCloud(int numCloud, Student student){
        try{
        clouds.get(numCloud).addStudent(student);
        }
        catch(TooManyStudentsOnCloudException e){
            // CODICE GESTIONE ERRORE
        }
    }

    public int getMotherNature() {
        return motherNature;
    }


    public void setMotherNature(int motherNature) {
        this.motherNature = motherNature;
    }

    public Bag getBag() {
        return bag;
    }

    public ArrayList<Cloud> getClouds() {
        return clouds;
    }

    public void islandFusion(int island1, int island2) {

        for(Student s: islands.get(island2).getStudents())
            islands.get(island1).addStudent(s);

        this.islands.get(island1).setNumTower(this.islands.get(island1).getNumTower() + this.islands.get(island2).getNumTower());
       // this.islands.get(island1).setNumTower(this.islands.get(island1).getIslandRank() + 1);

        islands.remove(island2);

    }

    public boolean getOneCoin(){
        if(this.generalReserve > 0){
            this.generalReserve--;
            return true;
        } else
            return false;
    }

    public int getGeneralReserve() {
        return generalReserve;
    }

    public void addCoinsToGeneralReserve(int coins) {
        this.generalReserve += coins;
    }

    public ArrayList<Island> getIslands() {
        return islands;
    }
}
