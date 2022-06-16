package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.utils.gameMessage;
import it.polimi.ingsw.view.RemoteView;
import it.polimi.ingsw.view.View;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server class is the main one of the server side, it allows clients to connect, play together and
 * also starts the match.
 *
 */


public class Server {

    private static final int PORT = 1337;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);

    /**
     * This hashmap permits to finding client ID relying on his unique nickname.
     * The client has to be connected to the server.
     */
    private Map<String, ClientConnection> waitingConnection = new HashMap<>();
    private Map<ClientConnection, ClientConnection> playingConnection = new HashMap<>();

    private ArrayList<String> nicknames = new ArrayList<>();
    private boolean chooseMode;
    private int numPlayers;
    private boolean expertMode;


    /**
     * Method deregisterClient deletes a client from the hashmaps and active lists, unregistering his
     * connection with the server
     *
     * @param c of type ClientConnection
     */



    //Deregister connection
    public synchronized void deregisterConnection(ClientConnection c) {
        ClientConnection opponent = playingConnection.get(c);
        ClientConnection opponent2 = playingConnection.get(opponent);

        if(opponent != null) {
            opponent.closeConnection();
        }

        if(opponent2 != null) {
            opponent2.closeConnection();
        }

        playingConnection.remove(c);
        playingConnection.remove(opponent);
        playingConnection.remove(opponent2);
        Iterator<String> iterator = waitingConnection.keySet().iterator();
        while(iterator.hasNext()){
            if(waitingConnection.get(iterator.next())==c){
                iterator.remove();
            }
        }
    }

    /**
     * Method setParameters permits you to set Gamemode and num of players parameters
     * @param numPlayers num of players in the game
     * @param expertMode Gamemode
     */
    public void setParameters(int numPlayers, boolean expertMode){
        this.numPlayers = numPlayers;
        this.expertMode = expertMode;
    }


    /**
     * Method lobby creates or handle a lobby, which is a common room used before a match. In this
     * room, connected players are waiting for other ones, in order to reach the correct players'
     * number for playing. If the waiting clients' queue is empty, the server creates a new lobby and
     * ask the first player to choose the capacity. After that, when a client connects, it checks if
     * the players number has been reached; if true, starts the match.
     *
     *
     * @param c of type Client Connection
     * @param name of type String
     * they are both used together in waitingConnection, very helpfull to associate name to a connection
     */



    public synchronized void lobby(ClientConnection c, String name){
        List<String> keys = new ArrayList<>(waitingConnection.keySet());
        for(int i = 0; i < keys.size(); i++){
            ClientConnection connection = waitingConnection.get(keys.get(i));
            connection.asyncSend("Connected User: " + keys.get(i));
        }
        waitingConnection.put(name, c);
        nicknames.add(name);
        keys = new ArrayList<>(waitingConnection.keySet());

        if (waitingConnection.size() == 2 && numPlayers == 2) {
            Controller controller = new Controller();
            controller.setParameters(numPlayers, expertMode);
            Model model = controller.getModel();

            ClientConnection c1 = waitingConnection.get(keys.get(1));
            ClientConnection c2 = waitingConnection.get(keys.get(0));
            Player player1 = new Player(keys.get(1), 0);
            Player player2 = new Player(keys.get(0), 1);
            View player1View = new RemoteView(player1, keys.get(0), c1);
            View player2View = new RemoteView(player2, keys.get(1), c2);

            controller.addPlayer(player1);
            controller.addPlayer(player2);
            controller.init();
            model.addObserver(player1View);
            model.addObserver(player2View);
            player1View.addObserver(controller);
            player2View.addObserver(controller);
            playingConnection.put(c1, c2);
            playingConnection.put(c2, c1);
            waitingConnection.remove(keys.get(1));
            waitingConnection.remove(keys.get(0));
            c1.asyncSend(model);
            c2.asyncSend(model);

            if(model.getCurrentPlayer() == player1.getIndex()){
                c1.asyncSend(gameMessage.moveMessage);
                c2.asyncSend(gameMessage.waitMessage);
            } else {
                c2.asyncSend(gameMessage.moveMessage);
                c1.asyncSend(gameMessage.waitMessage);
            }


        } else if (waitingConnection.size() == 3 && numPlayers == 3) {
            Controller controller = new Controller();
            controller.setParameters(numPlayers, expertMode);
            Model model = controller.getModel();

            ClientConnection c1 = waitingConnection.get(keys.get(2));
            ClientConnection c2 = waitingConnection.get(keys.get(1));
            ClientConnection c3 = waitingConnection.get(keys.get(0));
            Player player1 = new Player(keys.get(2), 0);
            Player player2 = new Player(keys.get(1), 1);
            Player player3 = new Player(keys.get(0), 2);
            View player1View = new RemoteView(player1, keys.get(1) + " and " + keys.get(0), c1);
            View player2View = new RemoteView(player2, keys.get(2) + " and " + keys.get(0), c2);
            View player3View = new RemoteView(player3, keys.get(2) + " and " + keys.get(1), c3);

            controller.addPlayer(player1);
            controller.addPlayer(player2);
            controller.addPlayer(player3);
            controller.init();
            model.addObserver(player1View);
            model.addObserver(player2View);
            model.addObserver(player3View);
            player1View.addObserver(controller);
            player2View.addObserver(controller);
            player3View.addObserver(controller);
            playingConnection.put(c1, c2);
            playingConnection.put(c2, c3);
            playingConnection.put(c3, c1);
            waitingConnection.remove(keys.get(2));
            waitingConnection.remove(keys.get(1));
            waitingConnection.remove(keys.get(0));
            c1.asyncSend(model);
            c2.asyncSend(model);
            c3.asyncSend(model);

            if(model.getCurrentPlayer() == player1.getIndex()){
                c1.asyncSend(gameMessage.moveMessage);
                c2.asyncSend(gameMessage.waitMessage);
                c3.asyncSend(gameMessage.waitMessage);
            } else if(model.getCurrentPlayer() == player2.getIndex()){
                c1.asyncSend(gameMessage.waitMessage);
                c2.asyncSend(gameMessage.moveMessage);
                c3.asyncSend(gameMessage.waitMessage);
            } else {
                c1.asyncSend(gameMessage.waitMessage);
                c2.asyncSend(gameMessage.waitMessage);
                c3.asyncSend(gameMessage.moveMessage);
            }
        }
    }


    /**
     * Server method
     * @throws IOException
     */
    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }


    /**
     *Run Method
     */
    public void run(){
        int connections = 0;
        System.out.println("Server is running");
        while(true){
            try {
                Socket newSocket = serverSocket.accept();
                connections++;
                System.out.println("Ready for the new connection - " + connections);
                SocketClientConnection socketConnection = new SocketClientConnection(newSocket, this);
                executor.submit(socketConnection);
            } catch (IOException e) {
                System.out.println("Connection Error!");
            }
        }
    }


    /**
     * Choosing gamemode status
     * @return chooseMode of type boolean
     */
    public boolean isChooseMode() {
        return chooseMode;
    }


    /**
     * setting gamemode status
     * @param chooseMode of type boolean
     */
    public void setChooseMode(boolean chooseMode) {
        this.chooseMode = chooseMode;
    }


    public Map<String, ClientConnection> getWaitingConnection() {
        return waitingConnection;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public ArrayList<String> getNicknames() {
        return nicknames;
    }
}
