package it.polimi.ingsw.model.gameboard.Characters;

public class Character8 implements CharacterCard{
    private int cost;

    public Character8() {
        this.cost = 1; //mettere valore carta
    }

    @Override
    public void play() {
        this.cost++;
    }
}