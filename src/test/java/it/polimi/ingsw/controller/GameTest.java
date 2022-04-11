package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

public class GameTest {

    @Test
    public void ControllerGameTest(){
        Parameters.setParameters(2,true);
        Game game = new Game();

        assertNotNull(game.getGameBoard());
        assertNotNull(game.getPlayers());

        game.addPlayer("gius");
        game.addPlayer("mef");

        game.init();

        game.playAssistantCard(0,9);
        game.playAssistantCard(1,5);
        assertEquals(1, game.getCurrentPlayer());

    }
}
