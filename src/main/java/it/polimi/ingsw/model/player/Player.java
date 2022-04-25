package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.TowerColour;

import java.util.ArrayList;

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
        this.towerColour = TowerColour.values()[(index)];
        this.playerSchoolBoard = new SchoolBoard(TowerColour.values()[(index)]);
        this.assistantDeck = new ArrayList<>();
        this.index = index;
        this.coins = 500;


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

    public void removeCoin(int cost){
        coins -= cost;
    }

    public int getCoins() {
        return coins;
    }

    @Override
    public String toString() {
        return "Player : " + index + " " +nickname;
    }

    public String getNickname() {
        return nickname;
    }
}





