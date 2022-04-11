package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.player.Tower;
import it.polimi.ingsw.model.enumeration.TowerColour;

import static org.junit.Assert.*;
import org.junit.Test;

public class TowerTest{
    @Test
    public void testTower(){
        Tower tower = new Tower(TowerColour.Black);
        assertEquals(TowerColour.Black,tower.getTowerColor());
        Tower tower1 = new Tower(TowerColour.White);
        assertEquals(TowerColour.White,tower1.getTowerColor());
        Tower tower2 = new Tower(TowerColour.Grey);
        assertEquals(TowerColour.Grey,tower2.getTowerColor());

    }



}
