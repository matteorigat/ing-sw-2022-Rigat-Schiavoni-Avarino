package it.polimi.ingsw.model.gameboard.Characters;

public class Character12 implements CharacterCard{
    private int index;
    private int cost;

    public Character12() {
        this.index = 1;
        this.cost = 1; //mettere valore carta
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

}
