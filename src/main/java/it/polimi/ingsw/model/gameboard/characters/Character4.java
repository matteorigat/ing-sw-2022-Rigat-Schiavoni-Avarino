package it.polimi.ingsw.model.gameboard.characters;

/** Character card 4 effect : You may move Mother Nature up to 2
 * additional Island than is indicated by the Assistant card you've played*/
public class Character4 implements CharacterCard{
    private int index;
    private int cost;
    private boolean effectFlag;

    private static final String Description = "You can move mother nature up to 2 additional islands.";

    public Character4(){
        this.index = 4;
        this.cost = 1;
        this.effectFlag = false;
    }

    /**
     * enableEffect method enables the effect of the card
     */
    public void enableEffect(){
        effectFlag = true;
    }

    /**
     * disableEffect method disables the effect of the card
     */
    public void disableEffect(){
        effectFlag = false;
    }

    /**
     * isEffectFlag returns the effectFlag
     * @return effectFlag
     */
    public boolean isEffectFlag() {
        return effectFlag;
    }

    /**
     * play method increases the cost of the card
     */
    @Override
    public void play() {
        this.cost++;
    }

    /**
     * getIndex method returns the index
     * @return index
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * getCost method returns the cost
     * @return cost
     */
    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return  "index: " + index +
                "\tcost: " + cost +
                "\t\teffectFlag: " + effectFlag +  "\t\t\t\t" + Description;
    }

    /**
     * getDescription method return the description
     * @return description
     */
    @Override
    public String getDescription(){
        return Description;
    }
}
