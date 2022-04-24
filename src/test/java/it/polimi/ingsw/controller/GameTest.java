package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.gameboard.Island;
import it.polimi.ingsw.model.gameboard.characters.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameTest {

    @Test
    public void ControllerGameTest(){
        Game game = new Game();
        game.setParameters(2,true);

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
        assertEquals((mn + 3)%game.getGameBoard().getIslands().size(), game.getGameBoard().getMotherNature());

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
        game.playAssistantCard(0,1);
        assertEquals(0, game.getCurrentPlayer());

        for(int i=0; i<50; i++){
            game.getPlayers().get(0).addCoin();
        }
        for (CharacterCard c: game.getGameBoard().getThreeCharacterCards()){
            if(c.getIndex() == 1){
                for(Island isl: game.getGameBoard().getIslands()){
                    if (isl.getStudents().size() == 1){
                        if(game.playCharacterCard1(0,1,0,isl.getIslandIndex()) == 1){
                            assertEquals(2, isl.getStudents().size());
                            assertEquals(2, c.getCost());
                        } else if(game.playCharacterCard1(0,1,1,isl.getIslandIndex()) == 1){
                            assertEquals(2, isl.getStudents().size());
                            assertEquals(2, c.getCost());
                        } else if(game.playCharacterCard1(0,1,2,isl.getIslandIndex()) == 1){
                            assertEquals(2, isl.getStudents().size());
                            assertEquals(2, c.getCost());
                        } else if(game.playCharacterCard1(0,1,3,isl.getIslandIndex()) == 1){
                            assertEquals(2, isl.getStudents().size());
                            assertEquals(2, c.getCost());
                        } else if(game.playCharacterCard1(0,1,4,isl.getIslandIndex()) == 1){
                            assertEquals(2, isl.getStudents().size());
                            assertEquals(2, c.getCost());
                        }
                        break;
                    }
                }
            } else if(c.getIndex() == 3){
                Colour profColor = game.getPlayers().get(0).getPlayerSchoolBoard().getProfessors().get(0).getProfessorColour();
                for(Island isl: game.getGameBoard().getIslands()){
                     if(isl.getStudents().size() == 1 && isl.getStudents().get(0).getColour() == profColor){
                         int numIsland = game.getGameBoard().getIslands().size();
                         assertEquals(1, game.playCharacterCard3(0,3, isl.getIslandIndex()));
                         assertEquals(4, c.getCost());
                         numIsland -= game.getGameBoard().getIslands().size();
                         numIsland = isl.getIslandIndex()-numIsland;
                         if(numIsland < 0){
                             int cont = 0;
                             while (numIsland != 0)
                                 cont++;
                             numIsland = game.getGameBoard().getIslands().size()-cont;
                         }
                         assertEquals(game.getPlayers().get(0).getPlayerSchoolBoard().getTowerColor(), game.getGameBoard().getIslands().get(numIsland).getTowerColor());
                         break;
                     }
                 }
            } else if(c.getIndex() == 4){
                color = game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
                game.moveStudentToDiningRoom(0, color);
                color = game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
                game.moveStudentToDiningRoom(0, color);
                color = game.getPlayers().get(game.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
                game.moveStudentToDiningRoom(0, color);
                mn = game.getGameBoard().getMotherNature();
                assertEquals(-1, game.moveMotherNature(0,3));
                assertEquals(1, game.playCharacterCard4(0, 4));
                assertEquals(1, game.moveMotherNature(0,3));
                assertEquals((mn + 3)%game.getGameBoard().getIslands().size(), game.getGameBoard().getMotherNature());
                assertEquals(2, c.getCost());

            } else if(c.getIndex() == 5){
                 assertEquals(0, game.getGameBoard().getIslands().get(0).getNoEntry());
                 assertEquals(1, game.playCharacterCard5(0,5,0));
                 assertEquals(1, game.getGameBoard().getIslands().get(0).getNoEntry());
                 //come lo testo senza cabiare metà codice? si fa prima a testare dalla view
            } else if(c.getIndex() == 6){
                 assertFalse(((Character6) c).isEffectFlag());
                 assertEquals(1, game.playCharacterCard6(0,6));
                 assertTrue(((Character6) c).isEffectFlag());
                //come lo testo senza cabiare metà codice? si fa prima a testare dalla view
            } else if(c.getIndex() == 8){
                assertFalse(((Character8) c).isEffectFlag());
                assertEquals(1, game.playCharacterCard6(0,6));
                assertTrue(((Character8) c).isEffectFlag());
                //come lo testo senza cabiare metà codice? si fa prima a testare dalla view
            }
        }

    }
}
