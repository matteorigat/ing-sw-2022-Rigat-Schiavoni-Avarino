package it.polimi.ingsw.model.gameboard.characters;

/** Character card 3 effect : Choose an island and resolve the island as if Mother Nature had ended her movement there.
 * Mother nature  will still move and the island where she ends her movement will also be resolved*/
public class Character3 implements CharacterCard{
    private int index;
    private int cost;
    private boolean effectFlag;

    private static final String Description = "Choose an island and resolve the island as if mother nature had ended her movement there.";

    /**
     * Character 3 card constructor
     */
    public Character3() {
        this.index = 3;
        this.cost = 3;
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
