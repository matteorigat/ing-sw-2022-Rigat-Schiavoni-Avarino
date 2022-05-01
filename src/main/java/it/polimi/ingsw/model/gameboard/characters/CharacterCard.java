package it.polimi.ingsw.model.gameboard.characters;

import java.io.Serializable;

public interface CharacterCard extends Serializable {

    public void play();

    public int getIndex();

    public int getCost();

}
