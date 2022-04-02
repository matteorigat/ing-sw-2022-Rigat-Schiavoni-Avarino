package it.polimi.ingsw.model.gameboard.Characters;

public class Character7 implements CharacterCard{
    private int cost;

    public Character7() {
        this.cost = 1; //mettere valore carta
    }

    @Override
    public void play() {
        this.cost++;
    }

}
