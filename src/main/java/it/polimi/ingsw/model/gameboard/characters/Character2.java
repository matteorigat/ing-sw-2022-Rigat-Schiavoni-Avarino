package it.polimi.ingsw.model.gameboard.characters;

/** Character card 2 effect : During this turn, you take contol of any number of Professors even
 *  if you have the same number of Students as the player who currently controls them */
public class Character2 implements CharacterCard{
    private int index;
    private int cost;
    private boolean effectFlag;

    private static final String Description = "Take control of any number of professors even if you have the same number of students.";

    /**
     * Constructor
     */
    public Character2() {
        this.index = 2;
        this.cost = 2;
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
