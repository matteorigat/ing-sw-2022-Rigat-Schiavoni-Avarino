package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.TowerColour;

import java.io.Serializable;

/**
 * Tower class defines Towers and create instance of them
 */
public class Tower implements Serializable {
    private TowerColour towerColor;

    /** Tower constructor */
    public Tower(TowerColour towerColor) {
        this.towerColor = towerColor;
    }

    /**
     * Method getTowerColour returns the Colour of the tower.
     * @return towercolour colour of the tower
     */
    public TowerColour getTowerColor() {
        return towerColor;
    }


}
