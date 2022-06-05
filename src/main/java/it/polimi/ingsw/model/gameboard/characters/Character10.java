package it.polimi.ingsw.model.gameboard.characters;

public class Character10 implements CharacterCard{
    private int index;
    private int cost;

    private static final String Description = "Scambia tra loro fino a due studenti della sala e dell'ingresso";

    public Character10() {
        this.index = 10;
        this.cost = 1;
    }

    @Override
    public void play() {
        this.cost++;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return  "index: " + index +
                "\tcost: " + cost +  "\t\t\t\t\t\t\t\t\t\t" + Description;
    }
    @Override
    public String getDescription(){
        return Description;
    }
}