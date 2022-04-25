package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.Parameters;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.gameboard.Cloud;
import it.polimi.ingsw.model.gameboard.GameBoard;
import it.polimi.ingsw.model.gameboard.Island;
import it.polimi.ingsw.model.gameboard.characters.CharacterCard;
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
                    System.out.print("Island: " + gb.getIslands().indexOf(i) + " \033[5;31m  M \033[0m  |\t");
                } else if(i.getIslandIndex() == gb.getMotherNature()){
                    System.out.print("Island: " + gb.getIslands().indexOf(i) + " \033[5;31m  M \033[0m  |\t");
                } else
                    System.out.print("Island: " + gb.getIslands().indexOf(i) + "\t |\t");

                System.out.print("Towers: " + i.getNumTower() + "\t|\t");
                System.out.print("Colour: " + i.getTowerColor() + "\t|\t");

                for(Student s : i.getStudents()) {
                    int colour = s.getColour().ordinal();
                    String str = "";
                    switch(colour) {
                        case (0) : { str = "\033[38;2;31;224;44m"; break;}
                        case (1) : {str = "\033[31m"; break;}
                        case (2) : {str = "\033[93m"; break;}
                        case (3) : {str = "\033[38;2;249;177;250m"; break;}
                        case (4) : {str = "\033[95m"; break;}

                    }
                            System.out.print(str + s.getColour() + "\033[0m" + " 🟢");

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
                if(Parameters.expertMode){
                    System.out.print("\nCOINS: " + p.getCoins());
                }
                System.out.println("\n");
            }

            if(Parameters.expertMode){
                ArrayList<CharacterCard> chtrs = (ArrayList<CharacterCard>) controller.getGameBoard().getThreeCharacterCards().clone();
                for (CharacterCard c:  chtrs)
                    System.out.println("CharacterCard | " + c.toString());
                System.out.print("\n");
            }

            for(int i=0; i<Parameters.numPlayers; i++){
                System.out.print("Cloud index: " + i + " | ");
                ArrayList<Cloud> clds = (ArrayList<Cloud>) controller.getGameBoard().getClouds().clone();
                for (Student s:  clds.get(i).seeStudents()){
                    System.out.print(s.getColour() + " ");
                }
                System.out.print("\n");
            }


            if(Parameters.expertMode){
                System.out.println("\nPHASE: " + GamePhase.values()[controller.getCurrentPhase()] + "... or write 'c' to play a character card");
                boolean characterbool = false;
            } else {
                System.out.println("\nPHASE: " + GamePhase.values()[controller.getCurrentPhase()]);
            }
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
                        if(Parameters.expertMode && choice.equals("c")) break;
                        int c = (int)choice.charAt(0) - 48;
                        if(choice.length() > 1)
                            c = c * 10 + (int)choice.charAt(1) - 48;
                        result = controller.playAssistantCard(currentPlayer, c);
                        if(result == 1) resultbool = true;
                        break;
                    }

                    case 1 : if (result!=1){
                        System.out.print("Student color and eventually the Island index: ");
                        choice = scanner.next();
                        if(Parameters.expertMode && choice.equals("c")) break;
                        int c = (int)choice.charAt(0) - 48;
                        if(choice.length() == 1)
                            result = controller.moveStudentToDiningRoom(currentPlayer, c);
                        else if(choice.length() > 1){
                            int d = (int)choice.charAt(1) - 48;
                            if(choice.length() > 2)
                                d = d * 10 + (int)choice.charAt(2) - 48;
                            result = controller.moveStudentToIsland(currentPlayer, c, d);
                        }
                        if(result == 1) resultbool = true;
                        break;
                    }

                    case 2 : if(result!=1) {
                        System.out.print("Mother nature movements: ");
                        choice = scanner.next();
                        if(Parameters.expertMode && choice.equals("c")) break;
                        int c = (int)choice.charAt(0) - 48;
                        result = controller.moveMotherNature(currentPlayer, c);
                        if(result == 1) resultbool = true;
                        break;
                    }

                    case 3 : if(result!=1) {
                        System.out.print("Cloud index: ");
                        choice = scanner.next();
                        if(Parameters.expertMode && choice.equals("c")) break;
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

                int c, color, island;
                if(choice.equals("c")){
                    System.out.print("Character card index: ");
                    choice = scanner.next();
                    c = (int)choice.charAt(0) - 48;
                    if(choice.length() > 1)
                        c = c * 10 + (int)choice.charAt(1) - 48;

                    if(c == 1){
                        System.out.print("Insert the student color and island index: ");
                        choice = scanner.next();
                        color = (int)choice.charAt(0) - 48;
                        island = (int)choice.charAt(1) - 48;
                        if(choice.length() > 2)
                            island = island * 10 + (int)choice.charAt(2) - 48;
                        result = controller.playCharacterCard1(currentPlayer, c, color, island);
                        if(result == 1) resultbool = true;

                    }/*else if(c == 2){
                            result = controller.playCharacterCard2();
                            if(result == 1) resultbool = true;

                        }*/else if(c == 3){
                        System.out.print("Insert the island index: ");
                        choice = scanner.next();
                        island = (int)choice.charAt(0) - 48;
                        if(choice.length() > 1)
                            island = island * 10 + (int)choice.charAt(1) - 48;
                        result = controller.playCharacterCard3(currentPlayer, c, island);
                        if(result == 1) resultbool = true;

                    } else if(c == 4){
                        result = controller.playCharacterCard4(currentPlayer, c);
                        if(result == 1) resultbool = true;

                    } else if(c == 5){
                        System.out.print("Insert the island index: ");
                        choice = scanner.next();
                        island = (int)choice.charAt(0) - 48;
                        if(choice.length() > 1)
                            island = island * 10 + (int)choice.charAt(1) - 48;
                        result = controller.playCharacterCard5(currentPlayer, c, island);
                        if(result == 1) resultbool = true;

                    } else if(c == 6){
                        result = controller.playCharacterCard6(currentPlayer, c);
                        if(result == 1) resultbool = true;

                    }/*else if(c == 7){
                            result = controller.playCharacterCard7();
                            if(result == 1) resultbool = true;

                        }*/else if(c == 8){
                        result = controller.playCharacterCard8(currentPlayer, c);
                        if(result == 1) resultbool = true;

                    }/*else if(c == 9){
                            result = controller.playCharacterCard9();
                            if(result == 1) resultbool = true;

                        } else if(c == 10){
                            result = controller.playCharacterCard10();
                            if(result == 1) resultbool = true;

                        }*/else if(c == 11){
                        System.out.print("Insert the student color: ");
                        choice = scanner.next();
                        color = (int)choice.charAt(0) - 48;
                        result = controller.playCharacterCard11(currentPlayer, c, color);
                        if(result == 1) resultbool = true;

                    } else if(c == 12){
                        System.out.print("Insert the student color: ");
                        choice = scanner.next();
                        color = (int)choice.charAt(0) - 48;
                        result = controller.playCharacterCard12(currentPlayer, c, color);
                        if(result == 1) resultbool = true;

                    }
                }

                if(result!=1)
                    System.out.println("Wrong! Retry!");
            }
        }
    }
}

