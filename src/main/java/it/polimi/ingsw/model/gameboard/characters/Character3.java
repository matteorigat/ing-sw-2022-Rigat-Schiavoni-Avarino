package it.polimi.ingsw.model.gameboard.characters;

public class Character3 implements CharacterCard{
    private int index;
    private int cost;
    private boolean effectFlag;

    public Character3() {
        this.index = 3;
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
