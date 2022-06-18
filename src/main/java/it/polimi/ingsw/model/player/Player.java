package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.TowerColour;

import java.io.Serializable;
import java.util.ArrayList;
/** Player Class defines every player setup:
 * so it has a schoolboard, an assistant deck, a name, and coins */
public class Player implements Serializable {
    private int index;
    private TowerColour towerColour;
    private String nickname;
    private SchoolBoard playerSchoolBoard;
    private ArrayList<AssistantCard> assistantDeck;
    private AssistantCard currentCard;
    private int coins;

    /**
     *  Constructor Player creates a new Player instance
     * @param nickname
     * @param index
     */
    public Player(String nickname, int index) {
        this.nickname = nickname;
        this.towerColour = TowerColour.values()[(index)];
        this.playerSchoolBoard = new SchoolBoard(TowerColour.values()[(index)]);
        this.assistantDeck = new ArrayList<>();
        this.index = index;
        this.coins = 500;
        this.currentCard = new AssistantCard(0,0);

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

    /**
     * getIndex method returns index of the player
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * TowerColour method returns the player towers colour
     * @return the player's tower color
     */

    public TowerColour PlayerTowerColor(){
        return playerSchoolBoard.getTowerColor();
    }

    /**
     * getPlayerSchoolBoard returns the schoolBoard of the player
     * @return playerSchoolBoard
     */
    //
    public SchoolBoard getPlayerSchoolBoard() {
        return playerSchoolBoard;
    }

    /**
     *  getAssistantDeck method returns assistant deck
     * @return assistant deck
     */
    public ArrayList<AssistantCard> getAssistantDeck() {
        return assistantDeck;
    }

    /**
     * PlayAssistantCard play a card an remove it from the deck
     * @param cardPlayed
     */

    public void playAssistantCard(AssistantCard cardPlayed){
                this.currentCard = cardPlayed;
                assistantDeck.remove(cardPlayed);
    }

    /**
     * getCurrentCard method returns currentCard
     * @return CurrentCard
     */
    public AssistantCard getCurrentCard() {
        return currentCard;
    }

    /**
     * addCoin method adds a coin
     */
    public void addCoin() {
        coins++;
    }

    /**
     * removeCoin method removes a coin
     * @param cost
     */
    public void removeCoin(int cost){
        coins -= cost;
    }

    /**
     * getCoins method returns the number of Coins
     * @return coins
     */
    public int getCoins() {
        return coins;
    }


    @Override
    public String toString() {
        return "Player : " + index + " " +nickname;
    }

    /**
     * getNickname method returns a nickname
     *
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * setCurrentCard method sets the current assistant card
     * @param currentCard
     */
    public void setCurrentCard(AssistantCard currentCard) {
        this.currentCard = currentCard;
    }
}





