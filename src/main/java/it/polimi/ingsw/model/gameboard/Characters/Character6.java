package it.polimi.ingsw.model.gameboard.Characters;

import it.polimi.ingsw.model.gameboard.GameBoard;

public class Character6 implements CharacterCard{
    private int index;
    private int cost;
    private boolean effectFlag;

    public Character6() {
        this.index = 6;
        this.cost = 3;
        this.effectFlag = false;
    }

    public void enableEffect(){
        effectFlag = true;
    }

    public void disableEffect(){
        effectFlag = false;
    }

    public boolean isEffectFlag() {
        return effectFlag;
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
