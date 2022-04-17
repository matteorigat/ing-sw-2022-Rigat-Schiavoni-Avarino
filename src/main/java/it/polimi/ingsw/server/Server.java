package it.polimi.ingsw.server;

import it.polimi.ingsw.Connection;
import it.polimi.ingsw.ServerClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// server in grado di accettare più connessioni client

public class Server {
    private static final int PORT= 12345;
    private ServerSocket serverSocket;

    private ExecutorService executor = Executors.newFixedThreadPool(128);

    private List<Connection> connections = new ArrayList<Connection>();
    private Map<String, Connection> waitingConnection = new HashMap<>();
    private Map<Connection, Connection> playingConnection = new HashMap<>();

    private Map<Integer,Boolean>gameSettings = new HashMap<>();



    //Register connection
    private synchronized void registerConnection(Connection c){
        connections.add(c);
    }

    //Deregister connection
    public synchronized void deregisterConnection(Connection c){
        connections.remove(c);
        Connection opponent = playingConnection.get(c);
        if(opponent != null){
            opponent.closeConnection();
            playingConnection.remove(c);
            playingConnection.remove(opponent);
        }
    }


    //lobby da capire come gestirla, il primo player decide la modalità e il num di giocatori?
    public synchronized void lobby(Connection c, String name,int numOfPlayers,Boolean gameMode){



        waitingConnection.put(name, c);




        if(waitingConnection.size() == 2){
            List<String> keys = new ArrayList<>(waitingConnection.keySet());
            Connection c1 = waitingConnection.get(keys.get(0));
            Connection c2 = waitingConnection.get(keys.get(1));
            playingConnection.put(c1, c2);
            playingConnection.put(c2, c1);
            waitingConnection.clear();
        }
        if(waitingConnection.size() == 3){
            List<String> keys = new ArrayList<>(waitingConnection.keySet());
            Connection c1 = waitingConnection.get(keys.get(0));
            Connection c2 = waitingConnection.get(keys.get(1));
            Connection c3 = waitingConnection.get(keys.get(2));
            playingConnection.put(c1, c2);
            playingConnection.put(c1, c3);
            playingConnection.put(c2, c3);
            playingConnection.put(c2, c1);


            waitingConnection.clear();
        }


    }




    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public void startServer(){
        System.out.println("Server listening on port: " + PORT);


        while(true){
            try {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket, this);
                registerConnection(connection);
                executor.submit(connection);
            } catch (IOException e){
                System.err.println("Connection error!");
            }
        }
    }










}




