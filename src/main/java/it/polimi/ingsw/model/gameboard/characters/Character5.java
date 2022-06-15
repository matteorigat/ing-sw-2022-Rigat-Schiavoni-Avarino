package it.polimi.ingsw.model.gameboard.characters;

/** Character card 5 effect : Place a no Entry title on an Island of your choice . The first time Mother Nature ends her movement there
 * put the NoEntry title back onto this card DO NOT calculate influence on that island or place any towers*/
public class Character5 implements CharacterCard{
    private int index;
    private int cost;
    private int noEntry;

    private static final String Description = "Tessera divieto su un'isola";
    public Character5() {
        this.index = 5;
        this.cost = 2;
        this.noEntry = 4;
    }

    /**
     * play method increases the cost of the card
     */
    @Override
    public void play() {
        this.cost++;
        noEntry--;
    }

    public void addNoEntry(){
        noEntry++;
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
                "\t\tnoEntry: " + noEntry +  "\t\t\t\t\t\t" + Description;
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
