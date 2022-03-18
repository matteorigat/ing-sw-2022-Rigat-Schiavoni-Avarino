package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.NotExistingPlayerException;
import it.polimi.ingsw.model.enumeration.Colour;
import it.polimi.ingsw.model.enumeration.TowerColour;
import it.polimi.ingsw.model.gameboard.Bag;
import it.polimi.ingsw.model.gameboard.GameBoard;

import java.util.ArrayList;
import it.polimi.ingsw.model.enumeration.Colour;

/*
 *  In this class we manage the main actions of the match.
 */

public class Game {
    private final ArrayList<Player> players;
    private final GameBoard gameBoard;


    //Constructor Game creates a new Game instance
    public Game() {
        players = new ArrayList<>();
        gameBoard = new GameBoard();
    }

    //Gets the players of the match
    public ArrayList<Player> getPlayers() {
        return players;
    }


    //Gets the gameBoard instance
    public GameBoard getGameBoard() {
        return gameBoard;
    }


    public void init(Bag b){   //sto seguendo l'inizializzazione della partita
        double casual = Math.random()*11;
        int mn = (int) casual;

        gameBoard.setMotherNature(mn);  //posiziono madrenatura

        ArrayList<Student> arr = new ArrayList<>();  //creo studenti per le isole
        for (Colour c : Colour.values()) {
            for(int i=0;i<2;i++){
                Student s = new Student(c);
                arr.add(s);
            }
        }
        b.fill(arr);

        gameBoard.addFirstStudentOnIsland(mn);  //posiziono gli studenti

        for (Colour c : Colour.values()) {  //creo gli studenti per il sacchetto
            for(int i=0;i<24;i++){
                Student s = new Student(c);
                arr.add(s);
            }
        }
        b.fill(arr);
    }

    public void CreateNewPlayer(String nickname){
        int num = 0;
        for(Player p: players){
            num++;
        }

        if(num < 3) { // qui andrebbe messa una DEFINE globale cosi da rendere più scalabile l'applicazione
            Player p = new Player(nickname);
        }
        else{
            //qui bisogna mettere una exception che nella partita ci sono già 4 giocatori
        }
    }

}
