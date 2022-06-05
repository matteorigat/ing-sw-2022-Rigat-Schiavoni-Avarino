package it.polimi.ingsw.model.gameboard.characters;

public class Character2 implements CharacterCard{
    private int index;
    private int cost;
    private boolean effectFlag;

    private static final String Description = "Prendi il controllo dei professori anche in caso di parità di studenti";

    public Character2() {
        this.index = 2;
        this.cost = 2;
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
                "\t\teffectFlag: " + effectFlag +  "\t\t\t\t" + Description;
    }
    @Override
    public String getDescription(){
        return Description;
    }

}
