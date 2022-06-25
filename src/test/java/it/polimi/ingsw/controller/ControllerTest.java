package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Parameters;

import static org.junit.Assert.*;

import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.gameboard.Island;
import it.polimi.ingsw.model.gameboard.characters.Character2;
import it.polimi.ingsw.model.gameboard.characters.Character6;
import it.polimi.ingsw.model.gameboard.characters.Character8;
import it.polimi.ingsw.model.gameboard.characters.CharacterCard;
import it.polimi.ingsw.model.player.Player;
import org.junit.Test;

public class ControllerTest {

    @Test
    public void ControllerGameTest(){
        Controller controller = new Controller();
        controller.setParameters(2,true);

        assertNotNull(controller.getGameBoard());
        assertNotNull(controller.getPlayers());

        Player p1 = new Player("mef", 0);
        Player p2 = new Player("gius", 1);
        Player p3 = new Player("nico", 2);
        controller.addPlayer(p1);
        controller.addPlayer(p2);
        assertEquals(-1, controller.addPlayer(p3));

        controller.init();

        assertEquals(0, controller.getCurrentPhase());
        controller.playAssistantCard(0,9);
        assertEquals(-1, controller.playAssistantCard(0,5));
        assertEquals(-1, controller.playAssistantCard(1,9));
        controller.playAssistantCard(1,5);
        assertEquals(1, controller.getCurrentPlayer());

        int color;
        ///////////////// GIOCATORE 1 /////////////////
        assertEquals(1, controller.getCurrentPhase());
        color = controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        controller.moveStudentToDiningRoom(1, color);
        assertEquals(color, controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getProfessors().get(0).getProfessorColour().ordinal());

        color = controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        controller.moveStudentToDiningRoom(1, color);
        color = controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        controller.moveStudentToIsland(1, color, controller.getGameBoard().getMotherNature());
        assertEquals(color, controller.getGameBoard().getIslands().get(controller.getGameBoard().getMotherNature()).getStudents().get(0).getColour().ordinal());

        assertEquals(Parameters.entranceStudents-3, controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().size());

        int mn = controller.getGameBoard().getMotherNature();
        assertEquals(2, controller.getCurrentPhase());
        assertEquals(-1, controller.moveMotherNature(0, 2));
        assertEquals(-1, controller.moveMotherNature(1, 5));
        controller.moveMotherNature(1, 3);
        assertEquals((mn + 3)% controller.getGameBoard().getIslands().size(), controller.getGameBoard().getMotherNature());

        assertEquals(3, controller.getCurrentPhase());
        assertEquals(-1, controller.chooseCloud(0, 0));
        assertEquals(-1, controller.chooseCloud(1, 2));
        controller.chooseCloud(1, 0);
        assertEquals(Parameters.entranceStudents, controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().size());

        ///////////////// GIOCATORE 2 /////////////////

        assertEquals(1, controller.getCurrentPhase());
        color = controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        controller.moveStudentToDiningRoom(0, color);
        color = controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        controller.moveStudentToDiningRoom(0, color);
        color = controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
        controller.moveStudentToIsland(0, color, controller.getGameBoard().getMotherNature());
        assertEquals(2, controller.getGameBoard().getIslands().get(controller.getGameBoard().getMotherNature()).getStudents().size());

        assertEquals(Parameters.entranceStudents-3, controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().size());

        assertEquals(2, controller.getCurrentPhase());
        mn = controller.getGameBoard().getMotherNature();
        assertEquals(-1, controller.moveMotherNature(1, 2));
        assertEquals(-1, controller.moveMotherNature(0, 7));
        controller.moveMotherNature(0, 1);

        assertEquals(3, controller.getCurrentPhase());
        assertEquals(-1, controller.chooseCloud(1, 1));
        assertEquals(-1, controller.chooseCloud(0, 0));
        assertEquals(-1, controller.chooseCloud(0, 2));
        controller.chooseCloud(0, 1);
        assertEquals(Parameters.entranceStudents, controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().size());


        assertEquals(0, controller.getCurrentPhase());

        assertEquals(-1, controller.playAssistantCard(0,3));
        assertEquals(-1, controller.playAssistantCard(1,5));
        controller.playAssistantCard(1,8);
        controller.playAssistantCard(0,1);
        assertEquals(0, controller.getCurrentPlayer());




        controller.getModel().print("mef");
















        for(int i=0; i<50; i++){
            controller.getPlayers().get(0).addCoin();
        }
        for (CharacterCard c: controller.getGameBoard().getThreeCharacterCards()){
            if(c.getIndex() == 1){
                for(Island isl: controller.getGameBoard().getIslands()){
                    if (isl.getStudents().size() == 1){
                        if(controller.playCharacterCard1(0,1,0,isl.getIslandIndex()) == 1){
                            assertEquals(2, isl.getStudents().size());
                            assertEquals(2, c.getCost());
                        } else if(controller.playCharacterCard1(0,1,1,isl.getIslandIndex()) == 1){
                            assertEquals(2, isl.getStudents().size());
                            assertEquals(2, c.getCost());
                        } else if(controller.playCharacterCard1(0,1,2,isl.getIslandIndex()) == 1){
                            assertEquals(2, isl.getStudents().size());
                            assertEquals(2, c.getCost());
                        } else if(controller.playCharacterCard1(0,1,3,isl.getIslandIndex()) == 1){
                            assertEquals(2, isl.getStudents().size());
                            assertEquals(2, c.getCost());
                        } else if(controller.playCharacterCard1(0,1,4,isl.getIslandIndex()) == 1){
                            assertEquals(2, isl.getStudents().size());
                            assertEquals(2, c.getCost());
                        }
                        break;
                    }
                }
            }
            /*else if(c.getIndex() == 2){
                assertFalse(((Character2) c).isEffectFlag());
                assertEquals(1, controller.playCharacterCard2(0,2));
                assertTrue(((Character2) c).isEffectFlag());
            }*/
            else if(c.getIndex() == 3){
                Colour profColor = controller.getPlayers().get(0).getPlayerSchoolBoard().getProfessors().get(0).getProfessorColour();
                for(Island isl: controller.getGameBoard().getIslands()){
                     if(isl.getStudents().size() == 1 && isl.getStudents().get(0).getColour() == profColor){
                         int numIsland = controller.getGameBoard().getIslands().size();
                         assertEquals(1, controller.playCharacterCard3(0,3, isl.getIslandIndex()));
                         assertEquals(4, c.getCost());
                         numIsland -= controller.getGameBoard().getIslands().size();
                         numIsland = isl.getIslandIndex()-numIsland;
                         if(numIsland < 0){
                             int cont = 0;
                             while (numIsland != 0)
                                 cont++;
                             numIsland = controller.getGameBoard().getIslands().size()-cont;
                         }
                         assertEquals(controller.getPlayers().get(0).getPlayerSchoolBoard().getTowerColor(), controller.getGameBoard().getIslands().get(numIsland).getTowerColor());
                         break;
                     }
                 }
            } else if(c.getIndex() == 4){
                color = controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
                controller.moveStudentToDiningRoom(0, color);
                color = controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
                controller.moveStudentToDiningRoom(0, color);
                color = controller.getPlayers().get(controller.getCurrentPlayer()).getPlayerSchoolBoard().getStudentsEntrance().get(0).getColour().ordinal();
                controller.moveStudentToDiningRoom(0, color);
                mn = controller.getGameBoard().getMotherNature();
                assertEquals(-1, controller.moveMotherNature(0,3));
                assertEquals(1, controller.playCharacterCard4(0, 4));
                assertEquals(1, controller.moveMotherNature(0,3));
                assertEquals((mn + 3)%controller.getGameBoard().getIslands().size(), controller.getGameBoard().getMotherNature());
                assertEquals(2, c.getCost());

            } else if(c.getIndex() == 5){
                 assertEquals(0, controller.getGameBoard().getIslands().get(0).getNoEntry());
                 assertEquals(1, controller.playCharacterCard5(0,5,0));
                 assertEquals(1, controller.getGameBoard().getIslands().get(0).getNoEntry());
            }/* else if(c.getIndex() == 6){
                 assertFalse(((Character6) c).isEffectFlag());
                 assertEquals(1, controller.playCharacterCard6(0,6));
                 assertTrue(((Character6) c).isEffectFlag());
            } else if(c.getIndex() == 8){
                assertFalse(((Character8) c).isEffectFlag());
                assertEquals(1, controller.playCharacterCard6(0,6));
                assertTrue(((Character8) c).isEffectFlag());
            }*/
        }

    }
}
