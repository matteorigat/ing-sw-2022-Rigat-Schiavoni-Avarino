package it.polimi.ingsw.model.gameboard.characters;

public class Character5 implements CharacterCard{
    private int index;
    private int cost;
    private int noEntry;

    public Character5() {
        this.index = 5;
        this.cost = 2;
        this.noEntry = 4;
    }

    @Override
    public void play() {
        this.cost++;
        noEntry--;
    }

    public void addNoEntry(){
        noEntry++;
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
                "\tcost: " + cost +
                "\t\tnoEntry: " + noEntry;
    }
}
