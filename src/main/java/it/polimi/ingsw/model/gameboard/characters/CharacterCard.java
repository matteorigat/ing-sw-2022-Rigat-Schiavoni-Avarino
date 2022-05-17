package it.polimi.ingsw.model.gameboard.characters;

import java.io.Serializable;


/**
 * CharacterCard class defines an interface for all the character cards.
 *
 */
public interface CharacterCard extends Serializable {

    public void play();

    public int getIndex();

    public int getCost();

}
