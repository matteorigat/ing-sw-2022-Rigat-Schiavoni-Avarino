package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.TowerColour;

import java.io.Serializable;

public class Tower implements Serializable {
    private TowerColour towerColor;

    /** Tower constructor */
    public Tower(TowerColour towerColor) {
        this.towerColor = towerColor;
    }

    //Gets the tower color
    public TowerColour getTowerColor() {
        return towerColor;
    }


}
