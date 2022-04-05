package it.polimi.ingsw;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.gameboard.Island;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class IslandTest extends TestCase{

    @Test
    public void IslandMethodsTest(){

        Parameters.setParameters(2, true);
        Player p1 = new Player("mef", 0);
        Player p2 = new Player("gius", 1);
        ArrayList<Player> arr = new ArrayList<>();
        arr.add(p1);
        arr.add(p2);

        Island isl = new Island();

        assertNull(isl.Influence(arr));

    }
}
