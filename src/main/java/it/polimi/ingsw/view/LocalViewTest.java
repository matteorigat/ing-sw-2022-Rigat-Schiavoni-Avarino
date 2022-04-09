package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.gameboard.Cloud;
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
            System.out.println("-------------------------------------------------------");
            GameBoard gb = controller.getGameBoard();
            System.out.println("");

            for(Island i : gb.getIslands()){
                boolean bool = false;
                if(i.getIslandIndex() == gb.getMotherNature()){
                    System.out.print("Island: " + gb.getIslands().indexOf(i) + " M |\t");
                } else
                    System.out.print("Island: " + i.getIslandIndex() + "\t|\t");

                for(Student s : i.getStudents()) {
                    System.out.print(s.getColour() + " ");
                    bool = true;
                }
                    if(bool == false) {
                        System.out.print("\t");
                    }

                    System.out.print("\t|\t" + "Towers: " + i.getNumTower() + "\t|\t");
                    System.out.print("Colour: " + i.getTowerColor());
                    System.out.println("\n");
            }

            ArrayList<Player> arrPlr = controller.getPlayers();
            for(Player p : arrPlr){
                System.out.println("Player: " + p.getIndex() +  " " + p.getNickname());
                SchoolBoard sb = p.getPlayerSchoolBoard();
                System.out.print("ENTRANCE: ");
                for(Student t : sb.getStudentsEntrance()){
                    System.out.print(t.getColour() + " ");
                }
                System.out.print("\nDINING ROOM: ");
                DiningRoom dr = sb.getDiningRoom();
                System.out.print("GREEN: " + dr.getGreenStudents() + " | ");
                System.out.print("RED: " + dr.getRedStudents() + " | ");
                System.out.print("YELLOW: " + dr.getYellowStudents() + " | ");
                System.out.print("PINK: " + dr.getPinkStudents() + " | ");
                System.out.println("BLUE: " + dr.getBlueStudents());
                System.out.print("DECK: ");
                for(AssistantCard card : p.getAssistantDeck()){
                    System.out.print(card.getValue() + " ");
                }
                System.out.println("\n");
            }

            for(int i=0; i< Parameters.numPlayers; i++){
                System.out.print("Cloud index: " + i + " | ");
                ArrayList<Cloud> clds = (ArrayList<Cloud>) controller.getGameBoard().getClouds().clone();
                for (Student s:  clds.get(i).seeStudents()){
                    System.out.print(s.getColour() + " ");
                }
                System.out.print("\n");
            }



            System.out.println("\nPHASE: " + GamePhase.values()[controller.getCurrentPhase()]);
            System.out.println("CURRENT PLAYER: " + controller.getCurrentPlayer() + " -> " + controller.getPlayers().get(controller.getCurrentPlayer()).getNickname());
            System.out.println("Students in the bag: " + controller.getGameBoard().getBag().getSize());
            System.out.println("CHOOSE : ");
                     int result = -2;
                     boolean resultbool = false;
                     while(resultbool == false) {
                         choice = scanner.next();

                         char ch = choice.charAt(0);

                         switch (ch) {

                             case '1' : if(choice.length()>=3 && result!=1) {
                                // System.out.println(result);
                                 int b = (int)choice.charAt(1) -48;
                                 int c = (int)choice.charAt(2) -48;
                               //  System.out.println(ch + " " + b + " " +c);
                                 result = controller.playAssistantCard(b,c);
                                 if(result == 1) resultbool = true;
                               //  System.out.println(result);

                             }
                             case '2' : if (choice.length()>=4 && result!=1){

                                 int b = (int)choice.charAt(1) -48;
                                 int c = (int)choice.charAt(2) -48;
                                 int d = (int)choice.charAt(3) -48;
                                 result = controller.moveStudentToIsland(b,c,d);
                                 if(result == 1) resultbool = true;
                             }

                             case '3' : if (choice.length()>=3 && result!=1){

                                 int b = (int)choice.charAt(1) -48;
                                 int c = (int)choice.charAt(2) -48;
                                 result = controller.moveStudentToDiningRoom(b,c);
                                 if(result == 1) resultbool = true;
                             }

                             case '4' : if(choice.length()>=3 && result!=1) {
                                           int b = (int)choice.charAt(1) -48;
                                           int c = (int)choice.charAt(2) -48;
                                           result = controller.moveMotherNature(b,c);
                                           if(result == 1)
                                               resultbool = true;
                             }

                             case '5' : if(choice.length()>=3 && result!=1) {
                                 int b = (int)choice.charAt(1) -48;
                                 int c = (int)choice.charAt(2) -48;
                                 result = controller.chooseCloud(b,c);
                                 if(result == 1)
                                     resultbool = true;
                             }
                         }
                         if(result!=1)
                         System.out.println("RETRY!!!");
                     }
            }

        }


    }

