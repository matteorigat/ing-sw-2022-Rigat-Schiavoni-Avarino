package it.polimi.ingsw;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.gameboard.Island;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNull;

public class IslandTest2 {
    @Test
    public void islandTestMethods(){
        Parameters.setParameters(2, true);
        Player p1 = new Player("mef", 1);
        Player p2 = new Player("gius", 2);
        ArrayList<Player> arr = new ArrayList<>();
        arr.add(p1);
        arr.add(p2);

        Island isl = new Island();

        assertNull(isl.Influence(arr));
    }
}
