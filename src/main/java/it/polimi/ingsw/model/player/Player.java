package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.TowerColour;

import java.io.Serializable;
import java.util.ArrayList;
/** Player Class defines every player setup:
 * he has a schoolBoard, an AssistantDeck, a name, and a number of coins */
public class Player implements Serializable {
    private int index;
    private TowerColour towerColour;
    private String nickname;
    private SchoolBoard playerSchoolBoard;
    private ArrayList<AssistantCard> assistantDeck;
    private AssistantCard currentCard;
    private int coins;

    /**
     *  Constructor of Player creates a new Player instance
     * @param nickname
     * @param index
     */
    public Player(String nickname, int index) {
        this.nickname = nickname;
        this.towerColour = TowerColour.values()[(index)];
        this.playerSchoolBoard = new SchoolBoard(TowerColour.values()[(index)]);
        this.assistantDeck = new ArrayList<>();
        this.index = index;
        this.coins = 1;
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
     * getIndex method returns the index of the player in the game
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * TowerColour method returns the player's towers' colour
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
     *  getAssistantDeck method returns the assistant deck
     * @return assistant deck
     */
    public ArrayList<AssistantCard> getAssistantDeck() {
        return assistantDeck;
    }

    /**
     * PlayAssistantCard plays a card an remove it from the deck
     * @param cardPlayed
     */

    public void playAssistantCard(AssistantCard cardPlayed){
                this.currentCard = cardPlayed;
                assistantDeck.remove(cardPlayed);
    }

    /**
     * getCurrentCard method returns the currently played Card
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
     * getCoins method returns the number of coins
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
     * getNickname method returns the nickname
     *
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * setCurrentCard method sets the chosen assistant card
     * @param currentCard
     */
    public void setCurrentCard(AssistantCard currentCard) {
        this.currentCard = currentCard;
    }
}





