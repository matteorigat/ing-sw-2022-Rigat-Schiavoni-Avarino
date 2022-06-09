package it.polimi.ingsw.model.player;

import it.polimi.ingsw.observer.Observer;
import it.polimi.ingsw.server.ClientConnection;
import it.polimi.ingsw.view.RemoteView;
import it.polimi.ingsw.view.View;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PlayerMoveTest {
    @Test
    public void PlayerMoveTesting() {
        Player player = new Player("mef", 0);
        ClientConnection cc = new ClientConnection() {
            @Override
            public void closeConnection() {

            }

            @Override
            public void addObserver(Observer<String> observer) {

            }

            @Override
            public void asyncSend(Object message) {

            }
        };
        View view = new RemoteView(player, "gius", cc);


        PlayerMove pm = new PlayerMove(player,1,1, view);
        assertEquals(1, pm.getParam1());
        assertEquals(1, pm.getParam2());
        assertEquals(-1, pm.getParam3());
        assertEquals(-1, pm.getParam4());
        assertEquals(-1, pm.getParam5());
        assertEquals(-1, pm.getParam6());
        assertEquals(-1, pm.getParam7());
        assertEquals(-1, pm.getParam8());

        assertNotNull(pm.getPlayer());
        assertNotNull(pm.getView());

        pm = new PlayerMove(player,1,1,1, view);
        assertEquals(1, pm.getParam1());
        assertEquals(1, pm.getParam2());
        assertEquals(1, pm.getParam3());
        assertEquals(-1, pm.getParam4());
        assertEquals(-1, pm.getParam5());
        assertEquals(-1, pm.getParam6());
        assertEquals(-1, pm.getParam7());
        assertEquals(-1, pm.getParam8());

        assertNotNull(pm.getPlayer());
        assertNotNull(pm.getView());

        pm = new PlayerMove(player,1,1,1,1, view);
        assertEquals(1, pm.getParam1());
        assertEquals(1, pm.getParam2());
        assertEquals(1, pm.getParam3());
        assertEquals(1, pm.getParam4());
        assertEquals(-1, pm.getParam5());
        assertEquals(-1, pm.getParam6());
        assertEquals(-1, pm.getParam7());
        assertEquals(-1, pm.getParam8());

        assertNotNull(pm.getPlayer());
        assertNotNull(pm.getView());

        pm = new PlayerMove(player, 1,1,1, 1, 1, 1, view);
        assertEquals(1, pm.getParam1());
        assertEquals(1, pm.getParam2());
        assertEquals(1, pm.getParam3());
        assertEquals(1, pm.getParam4());
        assertEquals(1, pm.getParam5());
        assertEquals(1, pm.getParam6());
        assertEquals(-1, pm.getParam7());
        assertEquals(-1, pm.getParam8());

        assertNotNull(pm.getPlayer());
        assertNotNull(pm.getView());

        pm = new PlayerMove(player, 1,1,1,1,1, 1, 1, 1, view);
        assertEquals(1, pm.getParam1());
        assertEquals(1, pm.getParam2());
        assertEquals(1, pm.getParam3());
        assertEquals(1, pm.getParam4());
        assertEquals(1, pm.getParam5());
        assertEquals(1, pm.getParam6());
        assertEquals(1, pm.getParam7());
        assertEquals(1, pm.getParam8());


        assertNotNull(pm.getPlayer());
        assertNotNull(pm.getView());


    }
}