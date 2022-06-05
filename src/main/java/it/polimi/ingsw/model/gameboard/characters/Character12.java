package it.polimi.ingsw.model.gameboard.characters;

public class Character12 implements CharacterCard{
    private int index;
    private int cost;

    private static final String Description = "Scegli un colore, tutti i giocatori devono mettere 3 studenti di quel colore dalla sala al sacchetto";

    public Character12() {
        this.index = 12;
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

    @Override
    public String toString() {
        return  "index: " + index +
                "\tcost: " + cost +  "\t\t\t\t\t\t\t\t\t\t" + Description;
    }
    @Override
    public String getDescription(){
        return Description;
    }
}
