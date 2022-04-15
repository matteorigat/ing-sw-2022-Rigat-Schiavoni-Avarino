package it.polimi.ingsw.controller;

import static org.junit.Assert.*;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

public class GameTest {

    @Test
    public void ControllerGameTest(){
        Game game = new Game();
        game.setParameters(2,false);

        assertNotNull(game.getGameBoard());
        assertNotNull(game.getPlayers());

        game.addPlayer("gius");
        game.addPlayer("mef");
        assertEquals(-1, game.addPlayer("nico"));

        game.init();

        assertEquals(0, game.getCurrentPhase());
        game.playAssistantCard(0,9);
        assertEquals(-1, game.playAssistantCard(0,5));
        assertEquals(-1, game.playAssistantCard(1,9));
        game.playAssistantCard(1,5);
        assertEquals(1, game.getCurrentPlayer());

        int color;
        ///////////////// GIOCATORE 1 /////////////////
        assertEquals(1, game.getCurrentPhase());
        color = game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        game.moveStudentToDiningRoom(1, color);
        assertEquals(color, game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getProfessors().get(0).getProfessorColour().ordinal());

        color = game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        game.moveStudentToDiningRoom(1, color);
        color = game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        game.moveStudentToIsland(1, color, game.getGameBoard().getMotherNature());
        assertEquals(color, game.getGameBoard().getIslands().get(game.getGameBoard().getMotherNature()).getStudents().get(0).getColour().ordinal());

        assertEquals(Parameters.entranceStudents-3, game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().size());

        int mn = game.getGameBoard().getMotherNature();
        assertEquals(2, game.getCurrentPhase());
        assertEquals(-1, game.moveMotherNature(0, 2));
        assertEquals(-1, game.moveMotherNature(1, 5));
        game.moveMotherNature(1, 3);
        assertEquals((mn + 3)%Parameters.numIslands, game.getGameBoard().getMotherNature());

        assertEquals(3, game.getCurrentPhase());
        assertEquals(-1, game.chooseCloud(0, 0));
        assertEquals(-1, game.chooseCloud(1, 2));
        game.chooseCloud(1, 0);
        assertEquals(Parameters.entranceStudents, game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().size());

        ///////////////// GIOCATORE 2 /////////////////

        assertEquals(1, game.getCurrentPhase());
        color = game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        game.moveStudentToDiningRoom(0, color);
        color = game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        game.moveStudentToDiningRoom(0, color);
        color = game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        game.moveStudentToIsland(0, color, game.getGameBoard().getMotherNature());
        assertEquals(2, game.getGameBoard().getIslands().get(game.getGameBoard().getMotherNature()).getStudents().size());

        assertEquals(Parameters.entranceStudents-3, game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().size());

        assertEquals(2, game.getCurrentPhase());
        mn = game.getGameBoard().getMotherNature();
        assertEquals(-1, game.moveMotherNature(1, 2));
        assertEquals(-1, game.moveMotherNature(0, 7));
        game.moveMotherNature(0, 1);
        assertEquals((mn + 1)%Parameters.numIslands, game.getGameBoard().getMotherNature());

        assertEquals(3, game.getCurrentPhase());
        assertEquals(-1, game.chooseCloud(1, 1));
        assertEquals(-1, game.chooseCloud(0, 0));
        assertEquals(-1, game.chooseCloud(0, 2));
        game.chooseCloud(0, 1);
        assertEquals(Parameters.entranceStudents, game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().size());


        assertEquals(0, game.getCurrentPhase());

        assertEquals(-1, game.playAssistantCard(0,3));
        assertEquals(-1, game.playAssistantCard(1,5));
        game.playAssistantCard(1,8);
        game.playAssistantCard(0,3);
        assertEquals(0, game.getCurrentPlayer());
    }
}
