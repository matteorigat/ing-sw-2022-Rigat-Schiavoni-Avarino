package it.polimi.ingsw.model;

import it.polimi.ingsw.model.player.MoveMessage;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.GamePhase;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Cloud;
import it.polimi.ingsw.model.gameboard.GameBoard;
import it.polimi.ingsw.model.gameboard.Island;
import it.polimi.ingsw.model.gameboard.characters.CharacterCard;
import it.polimi.ingsw.model.player.*;
import it.polimi.ingsw.observer.Observable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model class is the main logic of Eriantys.
 * Here we contruct the gameBoard, an arraylist of players and we declare the players' turn order
 */
public class Model extends Observable<MoveMessage> implements Serializable {

    private ArrayList<Player> players;
    private GameBoard gameBoard;

    private Player winner;
    private String lastMove;

    private int currentPlayer;
    private GamePhase currentPhase;
    private Player[] playersTurnOrder;
    private int phaseCounter;
    private int playerPhaseCounter;

    private boolean expertMode;
    private int numPlayers;

    /**Constructor Model creates a new Model instance*/
    public Model() {
        this.players = new ArrayList<>();
        this.gameBoard = new GameBoard();

        this.playersTurnOrder = new Player[Parameters.numPlayers];
        this.phaseCounter = 0;
        this.playerPhaseCounter = 0;
    }


    public void performMove(Player player){
        notify(new MoveMessage(this, player));
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GamePhase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(GamePhase currentPhase) {
        this.currentPhase = currentPhase;
    }

    public Player[] getPlayersTurnOrder() {
        return playersTurnOrder;
    }

    public int getPhaseCounter() {
        return phaseCounter;
    }

    public void setPhaseCounter(int phaseCounter) {
        this.phaseCounter = phaseCounter;
    }

    public int getPlayerPhaseCounter() {
        return playerPhaseCounter;
    }

    public void setPlayerPhaseCounter(int playerPhaseCounter) {
        this.playerPhaseCounter = playerPhaseCounter;
    }

    /**Class print is used to show the status of the game (CLI).
     It shows the gameboard, the schoolboard of every players and the islands.*/
    public void print(String nickname){
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("");
        String operSys = System.getProperty("os.name").toLowerCase();

        for(Island i : gameBoard.getIslands()){
            if (operSys.contains("mac os x")) {
                if(gameBoard.getIslands().indexOf(i) == gameBoard.getMotherNature() && gameBoard.getIslands().indexOf(i) < 10){
                    System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "\033[5;31m     M\033[0m    |\t");
                } else if(gameBoard.getIslands().indexOf(i) == gameBoard.getMotherNature()){
                    System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "\033[5;31m    M\033[0m    |\t");
                } else if(i.getNoEntry() != 0){
                    System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "\033[5;31m   " + i.getNoEntry() + " \033[0m |\t");
                }else
                    System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "\t   |\t");
            } else {
                if(gameBoard.getIslands().indexOf(i) == gameBoard.getMotherNature() && gameBoard.getIslands().indexOf(i) < 10){
                    System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "     M    |\t");
                } else if(gameBoard.getIslands().indexOf(i) == gameBoard.getMotherNature()){
                    System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "    M    |\t");
                } else if(i.getNoEntry() != 0){
                    System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "   " + i.getNoEntry() + "  |\t");
                }else
                    System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "\t   |\t");
            }

            System.out.print("Towers: "+ textTower(i.getNumTower(), i.getTowerColor()) + "\t|\t");

            for(Student s : i.getStudents())
                System.out.print(textColor(s.getColour()) + " ");

            System.out.println("\n");
        }

        ArrayList<Player> arrPlr = players;
        for(Player p : arrPlr){
            System.out.println("PLAYER: " + p.getIndex() +  " " + p.getNickname());
            SchoolBoard sb = p.getPlayerSchoolBoard();
            System.out.print("ENTRANCE: ");
            for(Student t: sb.getStudentsEntrance()){
                System.out.print(textColor(t.getColour())+ " ");
            }
            System.out.print("\nDINING ROOM: ");
            DiningRoom dr = sb.getDiningRoom();
            if (operSys.contains("mac os x")) {
                System.out.print("\033[32mGreen\033[0m: " + dr.numOfStudentByColor(Colour.Green) + " | ");
                System.out.print("\033[31mRed\033[0m: " + dr.numOfStudentByColor(Colour.Red) + " | ");
                System.out.print("\033[93mYellow\033[0m: " + dr.numOfStudentByColor(Colour.Yellow) + " | ");
                System.out.print("\033[95mPink\033[0m: " + dr.numOfStudentByColor(Colour.Pink) + " | ");
                System.out.println("\033[34mBlue\033[0m: " + dr.numOfStudentByColor(Colour.Blue));
            } else {
                System.out.print("Green: " + dr.numOfStudentByColor(Colour.Green) + " | ");
                System.out.print("Red: " + dr.numOfStudentByColor(Colour.Red) + " | ");
                System.out.print("Yellow: " + dr.numOfStudentByColor(Colour.Yellow) + " | ");
                System.out.print("Pink: " + dr.numOfStudentByColor(Colour.Pink) + " | ");
                System.out.println("Blue: " + dr.numOfStudentByColor(Colour.Blue));
            }
            System.out.print("PROFESSORS: ");
            for(Professor pr: sb.getProfessors()){
                System.out.print(textColor(pr.getProfessorColour()) + " ");
            }
            System.out.print("\nTOWERS: " + textTower(sb.getTowers().size(), sb.getTowerColor()));

            System.out.print("\nPLAYED CARD: ");
            if(p.getCurrentCard().getValue() != 0)
                System.out.print(p.getCurrentCard().getValue() + " (" + p.getCurrentCard().getMovements() + " movements)");

            if(p.getNickname().equals(nickname)){
                System.out.print("\nDECK: ");
                for(AssistantCard card : p.getAssistantDeck()){
                    System.out.print(card.getValue() + " ");
                }
            }

            if(expertMode){
                System.out.print("\nCOINS: " + p.getCoins());
            }
            System.out.println("\n");
        }

        if(expertMode){
            ArrayList<CharacterCard> chtrs = (ArrayList<CharacterCard>) gameBoard.getThreeCharacterCards().clone();
            for (CharacterCard c:  chtrs)
                System.out.println("CharacterCard | " + c.toString());
            System.out.print("\n");
        }

        for(int i=0; i<numPlayers; i++){
            System.out.print("Cloud index: " + i + " | ");
            ArrayList<Cloud> clds = (ArrayList<Cloud>) gameBoard.getClouds().clone();
            for (Student s:  clds.get(i).seeStudents()){
                System.out.print(textColor(s.getColour()) + " ");
            }
            System.out.print("\n");
        }


        if(expertMode){
            System.out.println("\nPHASE: " + currentPhase + "... or write 'c' to play a character card");
            boolean characterbool = false;
        } else {
            System.out.println("\nPHASE: " + currentPhase);
        }
        System.out.println("CURRENT PLAYER: " + currentPlayer + " -> " + players.get(currentPlayer).getNickname());
        System.out.println("");
        System.out.println("Students in the bag: " + gameBoard.getBag().getSize());
        //System.out.println("Mn on: " + gameBoard.getMotherNature());
    }

    /** colours settings */
    private String textColor(Colour colour){
        int colorInt = colour.ordinal();

        String operSys = System.getProperty("os.name").toLowerCase();
        if (operSys.contains("mac os x")) {
            switch(colorInt) {
                case (0) : return "🟢"; //""\033[38;2;31;224;44mGreen\033[0m";
                case (1) : return "🔴"; //"\033[31mRed\033[0m";
                case (2) : return "🟡"; //"\033[93mYellow\033[0m";
                case (3) : return "🟣"; //"\033[38;2;249;177;250mPink\033[0m";
                case (4) : return "🔵"; //"\033[38;2;85;99;250mBlue\033[0m";
            }
        } else {
            switch(colorInt) {
                case (0) : return "green";//"\033[32m●\033[0m";
                case (1) : return "red";//"\033[31m●\033[0m";
                case (2) : return "yellow";//"\033[93m●\033[0m";
                case (3) : return "pink";//"\033[95m●\033[0m";
                case (4) : return "blue";//"\033[34m●\033[0m";
            }
        }

        return null;
    }

    /**
     * textTower method prints in CLI the correct location of every tower on the board
     * @param n number of towers
     * @param colour colour of towers
     * @return "s"  string
     */
    private String textTower(int n, TowerColour colour){
        String s = "";

        String operSys = System.getProperty("os.name").toLowerCase();

        if (operSys.contains("mac os x")) {
            for(int i = 0; i<n; i++)
                if(colour.equals(TowerColour.White)){
                    s = s + "🤍 ";
                } else if(colour.equals(TowerColour.Black)){
                    s = s + "🖤 ";
                } else if(colour.equals(TowerColour.Grey)){
                    s = s + "🤎 ";
                }
        } else {
            for(int i = 0; i<n; i++)
                if(colour.equals(TowerColour.White)){
                    s = s + "white ";//"\033[97m🀫\033[0m ";
                } else if(colour.equals(TowerColour.Black)){
                    s = s + "black ";//"\033[30m🀫\033[0m ";
                } else if(colour.equals(TowerColour.Grey)){
                    s = s + "grey ";//"\033[90m🀫\033[0m ";
                }
        }

        return s;
    }



    public void setModelParameters(int numPlayer, boolean expertMode) {
        this.numPlayers = numPlayer;
        this.expertMode = expertMode;
    }

    public boolean isExpertMode() {
        return expertMode;
    }

    public String getLastMove() {
        return lastMove;
    }

    public void setLastMove(String nickname, String move) {
        this.lastMove = nickname + move;
    }
}
