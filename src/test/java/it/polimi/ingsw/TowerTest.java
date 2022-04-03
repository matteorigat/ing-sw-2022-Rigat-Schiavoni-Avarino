package it.polimi.ingsw;

import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.enumeration.TowerColour;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class TowerTest extends TestCase {
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
