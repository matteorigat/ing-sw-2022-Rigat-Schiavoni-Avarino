package it.polimi.ingsw.model.gameboard.Characters;

import it.polimi.ingsw.model.gameboard.GameBoard;

public class Character3 implements CharacterCard{
    private int index;
    private int cost;

    public Character3() {
        this.index = 3;
        this.cost = 3;
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
