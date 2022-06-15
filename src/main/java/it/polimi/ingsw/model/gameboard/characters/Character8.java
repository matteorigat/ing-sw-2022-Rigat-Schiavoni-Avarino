package it.polimi.ingsw.model.gameboard.characters;

/** Character card 8 effect : during the influence calculation this turn, you count as having 2 more influence */
public class Character8 implements CharacterCard{
    private int index;
    private int cost;
    private boolean effectFlag;

    private static final String Description = "2 punti influenza addizionali";

    public Character8() {
        this.index = 8;
        this.cost = 2;

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