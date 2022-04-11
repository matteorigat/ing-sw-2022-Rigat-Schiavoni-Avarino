package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.enumeration.Colour;
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
            System.out.println("\n--------------------------------------------------------------------------------------------------------------");
            GameBoard gb = controller.getGameBoard();
            System.out.println("");

            for(Island i : gb.getIslands()){
                boolean bool = false;
                if(i.getIslandIndex() == gb.getMotherNature() && i.getIslandIndex() < 10){
                    System.out.print("Island: " + gb.getIslands().indexOf(i) + "  M |\t");
                } else if(i.getIslandIndex() == gb.getMotherNature()){
                    System.out.print("Island: " + gb.getIslands().indexOf(i) + " M |\t");
                } else
                    System.out.print("Island: " + gb.getIslands().indexOf(i) + "\t |\t");

                System.out.print("Towers: " + i.getNumTower() + "\t|\t");
                System.out.print("Colour: " + i.getTowerColor() + "\t|\t");

                for(Student s : i.getStudents()) {
                    System.out.print(s.getColour() + " ");
                    bool = true;
                }
                if(bool == false)
                    System.out.print("\t\t|");
                else
                    System.out.print("\t|");

                System.out.println("\n");
            }

            ArrayList<Player> arrPlr = controller.getPlayers();
            for(Player p : arrPlr){
                System.out.println("PLAYER: " + p.getIndex() +  " " + p.getNickname());
                SchoolBoard sb = p.getPlayerSchoolBoard();
                System.out.print("ENTRANCE: ");
                for(Student t: sb.getStudentsEntrance()){
                    System.out.print(t.getColour() + " ");
                }
                System.out.print("\nPROFESSORS: ");
                for(Professor pr: sb.getProfessors()){
                    System.out.print(pr.getProfessorColour() + " ");
                }
                System.out.print("\nDINING ROOM: ");
                DiningRoom dr = sb.getDiningRoom();
                System.out.print("Green: " + dr.numOfStudentByColor(Colour.Green) + " | ");
                System.out.print("Red: " + dr.numOfStudentByColor(Colour.Red) + " | ");
                System.out.print("Yellow: " + dr.numOfStudentByColor(Colour.Yellow) + " | ");
                System.out.print("Pink: " + dr.numOfStudentByColor(Colour.Pink) + " | ");
                System.out.println("Blue: " + dr.numOfStudentByColor(Colour.Blue));
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

            int result = -2;
            boolean resultbool = false;
            while(resultbool == false) {

                //questi due valori andranno presi dal client poi
                int phase = controller.getCurrentPhase();
                int currentPlayer = controller.getCurrentPlayer();

                switch (phase) {

                    case 0 : if(result!=1) {
                        System.out.print("Assistant card number: ");
                        choice = scanner.next();
                        // System.out.println(result);
                        int c = (int)choice.charAt(0) - 48;
                        if(choice.length() > 1)
                            c = c * 10 + (int)choice.charAt(1) - 48;
                        //  System.out.println(ch + " " + b + " " +c);
                        result = controller.playAssistantCard(currentPlayer, c);
                        if(result == 1) resultbool = true;
                        break;
                        //  System.out.println(result);
                    }

                    case 1 : if (result!=1){
                        System.out.print("Student color and eventually the Island index: ");
                        choice = scanner.next();
                        int c = (int)choice.charAt(0) - 48;
                        if(choice.length() == 1)
                            result = controller.moveStudentToDiningRoom(currentPlayer, c);
                        else if(choice.length() > 1){
                            int d = (int)choice.charAt(1) - 48;
                            if(choice.length() >= 3)
                                d = d * 10 + (int)choice.charAt(2) - 48;
                            result = controller.moveStudentToIsland(currentPlayer, c, d);
                        }
                        if(result == 1) resultbool = true;
                        break;
                    }

                    case 2 : if(result!=1) {
                        System.out.print("Mother nature movements: ");
                        choice = scanner.next();
                        int c = (int)choice.charAt(0) - 48;
                        result = controller.moveMotherNature(currentPlayer, c);
                        if(result == 1) resultbool = true;
                        break;
                    }

                    case 3 : if(result!=1) {
                        System.out.print("Cloud index: ");
                        choice = scanner.next();
                        int c = (int)choice.charAt(0) - 48;
                        result = controller.chooseCloud(currentPlayer,c);
                        if(result == 1) resultbool = true;
                        break;
                    }

                    case 5 : if(result!=1) {
                        String winner = controller.getTheWinner();
                        System.out.println(winner + "WON THE GAME!");
                        return;
                    }
                }
                if(result!=1)
                    System.out.println("Wrong! Retry!");
            }
        }
    }
}

