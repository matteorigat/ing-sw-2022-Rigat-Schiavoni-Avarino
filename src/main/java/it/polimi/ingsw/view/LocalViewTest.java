package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.gameboard.GameBoard;
import it.polimi.ingsw.model.gameboard.Island;
import it.polimi.ingsw.model.player.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LocalViewTest {

    Scanner scanner;
    private Game controller;
    private String choice;

    public LocalViewTest() {
        this.scanner = new Scanner(System.in);
    }

    public void setController(Game controller) {
        this.controller = controller;
    }

    public void start() {
        while(true){
            System.out.println("\n\n\n\n\n");
            System.out.println("CURRENT STATE");
            GameBoard gb = controller.getGameBoard();
            System.out.println("MOTHER NATURE ON ISLAND :" + gb.getMotherNature());

            for(Island i : gb.getIslands()){
                boolean bool = false;
                System.out.println("Island: " + i.getIslandIndex() );
                for(Student s : i.getStudents()) {
                    System.out.println(s.getColour());
                    bool = true;
                }
                    if(bool == false) {
                        System.out.println("NO STUDENTS");
                    }
                    System.out.println("Towers: " + i.getNumTower());
                    System.out.println("Colour of the tower: " + i.getTowerColor());
                    System.out.println("\n");
                }

            ArrayList<Player> arrPlr = controller.getPlayers();
            for(Player p : arrPlr){
                System.out.println("Player: " + p.getIndex() +  " " + p.getNickname());
                SchoolBoard sb = p.getPlayerSchoolBoard();
                System.out.println("ENTRANCE: ");
                for(Student t : sb.getStudentsEntrance()){
                    System.out.println(t.getColour());
                }
                System.out.println("DINING ROOM: ");
                DiningRoom dr = sb.getDiningRoom();
                System.out.println("GREEN: " + dr.getGreenStudents());
                System.out.println("RED: " + dr.getRedStudents());
                System.out.println("YELLOW: " + dr.getYellowStudents());
                System.out.println("PINK: " + dr.getPinkStudents());
                System.out.println("BLUE: " + dr.getBlueStudents());
                System.out.println("DECK: ");
                for(AssistantCard card : p.getAssistantDeck()){
                    System.out.println(card.getValue());
                }
                System.out.println("\n\n");
            }
            System.out.println("PHASE: " + GamePhase.values()[controller.getCurrentPhase()]);
            System.out.println("CURRENT PLAYER: " + controller.getCurrentPlayer() + " -> " + controller.getPlayers().get(controller.getCurrentPlayer()).getNickname());
            System.out.println("Students in the bag: " + controller.getGameBoard().getBag().getSize());
            System.out.println("CHOOSE : ");
                     int result = -2;
                     boolean resultbool = false;
                     while(resultbool == false) {
                         choice = scanner.next();

                         char ch = choice.charAt(0);

                         switch (ch) {

                             case '1' : if(choice.length()>=3) {
                                 System.out.println(result);
                                 int b = (int)choice.charAt(1) -48;
                                 int c = (int)choice.charAt(2) -48;
                                 System.out.println(ch + " " + b + " " +c);
                                 result = controller.playAssistantCard(b,c);
                                 if(result == 1) resultbool = true;
                                 System.out.println(result);

                             }
                             case '2' : if (choice.length()>=4){

                                 int b = (int)choice.charAt(1) -48;
                                 int c = (int)choice.charAt(2) -48;
                                 int d = (int)choice.charAt(3) -48;
                                 result = controller.moveStudentToIsland(b,c,d);
                                 if(result == 1) resultbool = true;
                             }
                             case '4' : if(choice.length()>=3 && result!=1) {
                                           int b = (int)choice.charAt(1) -48;
                                           int c = (int)choice.charAt(2) -48;
                                           result = controller.moveMotherNature(b,c);
                                           if(result == 1) {
                                               resultbool = true;

                                           }

                             }
                         }
                         System.out.println("PHASE: " + controller.getCurrentPhase());
                         System.out.println("CURRENT PLAYER: " + controller.getCurrentPlayer());
                         System.out.println("Students in the bag: " + controller.getGameBoard().getBag().getSize());
                     }
            }

        }


    }

