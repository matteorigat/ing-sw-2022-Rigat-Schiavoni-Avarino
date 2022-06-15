package it.polimi.ingsw.model.gameboard.characters;

/** Character card 10 effect : You may exchange up to 2 Students between your entrance and your dining room */
public class Character10 implements CharacterCard{
    private int index;
    private int cost;

    private static final String Description = "Scambia tra loro fino a due studenti della sala e dell'ingresso";

    public Character10() {
        this.index = 10;
        this.cost = 1;
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
                "\tcost: " + cost +  "\t\t\t\t\t\t\t\t\t\t" + Description;
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