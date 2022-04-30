package it.polimi.ingsw.model;

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

public class Model extends Observable<Model> implements Serializable {
    private ArrayList<Player> players;
    private GameBoard gameBoard;

    private Player winner;

    private int currentPlayer;
    private GamePhase currentPhase;
    private Player[] playersTurnOrder;
    private int phaseCounter;
    private int playerPhaseCounter;

    //Constructor Game creates a new Game instance
    public Model() {
        this.players = new ArrayList<>();
        this.gameBoard = new GameBoard();

        this.playersTurnOrder = new Player[Parameters.numPlayers];
        this.phaseCounter = 0;
        this.playerPhaseCounter = 0;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
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

    public void setPlayersTurnOrder(int i, Player playersTurnOrder) {
        this.playersTurnOrder[i] = playersTurnOrder;
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

    public void print(){
        System.out.println("");

        for(Island i : gameBoard.getIslands()){
            if(gameBoard.getIslands().indexOf(i) == gameBoard.getMotherNature() && gameBoard.getIslands().indexOf(i) < 10){
                System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "\033[5;31m   M \033[0m |\t");
            } else if(gameBoard.getIslands().indexOf(i) == gameBoard.getMotherNature()){
                System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "\033[5;31m  M\033[0m  |\t");
            } else if(i.getNoEntry() != 0){
                System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "\033[5;31m   " + i.getNoEntry() + " \033[0m |\t");
            }else
                System.out.print("Island: " + gameBoard.getIslands().indexOf(i) + "\t   |\t");

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
            System.out.print("\033[38;2;31;224;44mGreen\033[0m: " + dr.numOfStudentByColor(Colour.Green) + " | ");
            System.out.print("\033[31mRed\033[0m: " + dr.numOfStudentByColor(Colour.Red) + " | ");
            System.out.print("\033[93mYellow\033[0m: " + dr.numOfStudentByColor(Colour.Yellow) + " | ");
            System.out.print("\033[38;2;249;177;250mPink\033[0m: " + dr.numOfStudentByColor(Colour.Pink) + " | ");
            System.out.println("\033[38;2;85;99;250mBlue\033[0m: " + dr.numOfStudentByColor(Colour.Blue));
            System.out.print("PROFESSORS: ");
            for(Professor pr: sb.getProfessors()){
                System.out.print(textColor(pr.getProfessorColour()) + " ");
            }
            System.out.print("\nDECK: ");
            for(AssistantCard card : p.getAssistantDeck()){
                System.out.print(card.getValue() + " ");
            }
            if(Parameters.expertMode){
                System.out.print("\nCOINS: " + p.getCoins());
            }
            System.out.println("\n");
        }

        if(Parameters.expertMode){
            ArrayList<CharacterCard> chtrs = (ArrayList<CharacterCard>) gameBoard.getThreeCharacterCards().clone();
            for (CharacterCard c:  chtrs)
                System.out.println("CharacterCard | " + c.toString());
            System.out.print("\n");
        }

        for(int i=0; i<Parameters.numPlayers; i++){
            System.out.print("Cloud index: " + i + " | ");
            ArrayList<Cloud> clds = (ArrayList<Cloud>) gameBoard.getClouds().clone();
            for (Student s:  clds.get(i).seeStudents()){
                System.out.print(textColor(s.getColour()) + " ");
            }
            System.out.print("\n");
        }


        if(Parameters.expertMode){
            System.out.println("\nPHASE: " + currentPhase + "... or write 'c' to play a character card");
            boolean characterbool = false;
        } else {
            System.out.println("\nPHASE: " + currentPhase);
        }
        System.out.println("CURRENT PLAYER: " + currentPlayer + " -> " + players.get(currentPlayer).getNickname());
        System.out.println("Students in the bag: " + gameBoard.getBag().getSize());
        System.out.println("Mn on: " + gameBoard.getMotherNature());
    }

    private String textColor(Colour colour){
        int colorInt = colour.ordinal();
        switch(colorInt) {
            case (0) : return "🟢"; //""\033[38;2;31;224;44mGreen\033[0m";
            case (1) : return "🔴"; //"\033[31mRed\033[0m";
            case (2) : return "🟡"; //"\033[93mYellow\033[0m";
            case (3) : return "🟣"; //"\033[38;2;249;177;250mPink\033[0m";
            case (4) : return "🔵"; //"\033[38;2;85;99;250mBlue\033[0m";
        }
        return null;
    }

    private String textTower(int n, TowerColour colour){
        String s = "";
        for(int i = 0; i<n; i++)
            if(colour.equals(TowerColour.White)){
                s = s + "🤍 ";
            } else if(colour.equals(TowerColour.Black)){
                s = s + "🖤 ";
            } else if(colour.equals(TowerColour.Grey)){
                s = s + "🤎 ";
            }

        return s;
    }


}
