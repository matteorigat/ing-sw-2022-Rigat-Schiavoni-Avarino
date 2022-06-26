package it.polimi.ingsw.model.gameboard.characters;

import it.polimi.ingsw.model.enumeration.Colour;

/** Character card 9 effect : Choose a color of student: during the influence calculation this turn, that color adds no influence*/
public class Character9 implements CharacterCard{
    private int index;
    private int cost;
    private boolean effectFlag;

    private int color;

    private static final String Description = "Choose a colour: during the influence calculation this turn, that color adds no influence.";

    public Character9() {
        this.index = 9;
        this.cost = 3;
        this.color = -1;
        this.effectFlag = false;
    }

    /**
     * enableEffect method enables the effect of the card
     */
    public void enableEffect(int color){
        effectFlag = true;
        this.color = color;
    }

    /**
     * disableEffect method disables the effect of the card
     */
    public void disableEffect(){
        effectFlag = false;
        this.color = -1;
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

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return  "index: " + index +
                "\tcost: " + cost +
                "\t\teffectFlag: " + getColor() +  "\t\t\t\t" + Description;
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
