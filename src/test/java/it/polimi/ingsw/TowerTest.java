package it.polimi.ingsw;
import it.polimi.ingsw.model.Tower;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class TowerTest extends TestCase{

    @Test
    public void testTower(){
        Tower tower = new Tower(TowerColour.Black);
        assertEquals(tower.getTowerColor(), TowerColour.Black);
    }
}
