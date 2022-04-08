package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.Game;
import it.polimi.ingsw.model.gameboard.GameBoard;
import it.polimi.ingsw.model.gameboard.Island;
import it.polimi.ingsw.model.player.DiningRoom;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.model.player.SchoolBoard;
import it.polimi.ingsw.model.player.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class LocalViewTest {

    Scanner scanner;
    private Game controller;

    public LocalViewTest() {
        this.scanner = new Scanner(System.in);
    }

    public void setController(Game controller) {
        this.controller = controller;
    }

    public void start(){
        while(true){
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
                System.out.println("\n\n");
            }
            System.out.println("PHASE: " + controller.getCurrentPhase());
            System.out.println("CURRENT PLAYER: " + controller.getCurrentPlayer());
            System.out.println("CHOOSE : ");
            int choice = scanner.nextInt();

            }

        }


    }

