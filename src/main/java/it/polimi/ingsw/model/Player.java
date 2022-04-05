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
    private int coins;

    // Constructor Player creates a new Player instance
    public Player(String nickname, int index) {
        this.nickname = nickname;
        this.towerColour = TowerColour.values()[(index-1)];
        this.playerSchoolBoard = new SchoolBoard(TowerColour.values()[(index-1)]);
        this.assistantDeck = new ArrayList<>();
        this.index = index;
        this.coins = 1;


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

    public int getIndex() {
        return index;
    }

    //Return the player's tower color
    public TowerColour PlayerTowerColor(){
        return playerSchoolBoard.getTowerColor();
    }

    //Gets the schoolBoard of the player
    public SchoolBoard getPlayerSchoolBoard() {
        return playerSchoolBoard;
    }

    public ArrayList<AssistantCard> getAssistantDeck() {
        return assistantDeck;
    }

    // play a card and remove it from the deck
    public void playAssistantCard(AssistantCard cardPlayed){
                this.currentCard = cardPlayed;
                assistantDeck.remove(cardPlayed);
    }

    public AssistantCard getCurrentCard() {
        return currentCard;
    }

    public void addCoin() {
        coins++;
    }

    public int getCoins() {
        return coins;
    }
}




