package it.polimi.ingsw.model.player;

import it.polimi.ingsw.model.Model;

import static org.junit.Assert.*;
import org.junit.Test;

public class MoveMessageTest {
    @Test
    public void testMoveMessage() {

        Model model = new Model();
        Player player = new Player("mef", 0);
        MoveMessage moveMessage = new MoveMessage(model, player);

        assertNotNull(moveMessage.getModel());
        assertNotNull(moveMessage.getPlayer());
    }
}
