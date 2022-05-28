package it.polimi.ingsw.model.gameboard.characters;

import it.polimi.ingsw.model.enumeration.Colour;

public class Character9 implements CharacterCard{
    private int index;
    private int cost;
    private boolean effectFlag;

    private int color;

    private static final String Description = "Durante il calcolo dell'influenza il colore che scegli non viene contato";

    public Character9() {
        this.index = 9;
        this.cost = 3;
        this.color = -1;
        this.effectFlag = false;
    }

    public void enableEffect(int color){
        effectFlag = true;
        this.color = color;
    }

    public void disableEffect(){
        effectFlag = false;
        this.color = -1;
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

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return  "index: " + index +
                "\tcost: " + cost +
                "\t\teffectFlag: " + getColor() +  "\t\t\t\t" + Description;
    }
}
