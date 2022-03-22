package it.polimi.ingsw.model;

import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
    private int index;
    private TowerColour towerColour;
    private String nickname;
    private SchoolBoard playerSchoolBoard;
    private ArrayList<AssistantCard> assistantDeck;
    private AssistantCard currentCard;


    // Constructor Player creates a new Player instance
    public Player(String nickname, int index) {
        this.nickname = nickname;
        this.towerColour = TowerColour.values()[index];
        this.playerSchoolBoard = new SchoolBoard(this.towerColour);
        this.assistantDeck = new ArrayList<>();
        this.index = index;

        assistantDeck.add(new AssistantCard(1,1));
        assistantDeck.add(new AssistantCard(2,1));
        assistantDeck.add(new AssistantCard(3,2));
        assistantDeck.add(new AssistantCard(4,2));
        assistantDeck.add(new AssistantCard(5,3));
        assistantDeck.add(new AssistantCard(6,3));
        assistantDeck.add(new AssistantCard(7,4));
        assistantDeck.add(new AssistantCard(8,4));
        assistantDeck.add(new AssistantCard(9,5));
        assistantDeck.add(new AssistantCard(10,5));
    }


    //Return the player's tower color
    public TowerColour PlayerTowerColor(){
        return playerSchoolBoard.getTowerColor();
    }

    public ArrayList<Professor> getPlayerProfessors(){
        return playerSchoolBoard.getProfessors();
    }

    //Gets the schoolBoard of the player
    public SchoolBoard getPlayerSchoolBoard() {
        return playerSchoolBoard;
    }


    public ArrayList<AssistantCard> getAssistantDeck() {
        return assistantDeck;
    }
















    public void printDeck(){
        for(int i=0;i< assistantDeck.size();i++){
            System.out.println(assistantDeck.get(i).getValue() + " " + assistantDeck.get(i).getMovements());
        }
    }
    // play a card and remove it from the deck (da implementare le funzionalità di giocata)
    public void playCard(AssistantCard cardPlayed){
                this.currentCard = cardPlayed;
                assistantDeck.remove(cardPlayed);
            }




}




