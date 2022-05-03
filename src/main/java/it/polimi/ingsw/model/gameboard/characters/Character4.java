package it.polimi.ingsw.model.gameboard.characters;

public class Character4 implements CharacterCard{
    private int index;
    private int cost;
    private boolean effectFlag;

    private static final String Description = "Puoi muovere MN fino a 2 isole addizionali";

    public Character4(){
        this.index = 4;
        this.cost = 1;
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

    @Override
    public String toString() {
        return  "index: " + index +
                "\tcost: " + cost +
                "\t\teffectFlag: " + effectFlag +  "\t\t" + Description;
    }
}
