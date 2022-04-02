package it.polimi.ingsw.model.gameboard.Characters;

public class Character3 implements CharacterCard{
    private int cost;

    public Character3() {
        this.cost = 1; //mettere valore carta
    }

    @Override
    public void play() {
        this.cost++;
    }
}
