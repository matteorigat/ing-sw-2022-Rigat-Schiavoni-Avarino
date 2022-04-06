package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.enumeration.TowerColour;

public class Tower {
    private TowerColour towerColor;

    public Tower(TowerColour towerColor) {
        this.towerColor = towerColor;
    }

    //Gets the tower color
    public TowerColour getTowerColor() {
        return towerColor;
    }


}
