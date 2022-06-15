package it.polimi.ingsw.model.gameboard.characters;

/** Character card 12 effect : Choose a type of student: every player (including yourself) must return 3 students of that type from
 * the dining room to the bag . if any player has fewer than 3 students of that type, return as many students as they have */
public class Character12 implements CharacterCard{
    private int index;
    private int cost;

    private static final String Description = "Scegli un colore, tutti i giocatori devono mettere 3 studenti di quel colore dalla sala al sacchetto";


    public Character12() {
        this.index = 12;
        this.cost = 3;
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
