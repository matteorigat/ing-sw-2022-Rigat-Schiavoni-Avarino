package it.polimi.ingsw.model.gameboard.Characters;

public class Character2 implements CharacterCard{
    private int cost;

    public Character2() {
        this.cost = 1; //mettere valore carta
    }

    @Override
    public void play() {
        this.cost++;
    }
}
