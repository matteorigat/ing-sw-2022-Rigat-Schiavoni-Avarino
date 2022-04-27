package it.polimi.ingsw.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;

    public Server(int port){
        this.port = port;
    }

    public void startServer() throws IOException {
        //It creates threads when necessary, otherwise it re-uses existing one when possible
        ExecutorService executor = Executors.newCachedThreadPool();

        ServerSocket serverSocket;
        try{
            System.out.println("[0] Inizializzo il server...");
            serverSocket = new ServerSocket(port);
            System.out.println("[1] Server in ascolto sulla porta: " + port);
        }catch (IOException e){
            System.err.println(e.getMessage()); //port not available
            return;
        }

        while (true){
            try{
                Socket socket = serverSocket.accept();
                System.out.println("[2] connessione stabilita con un client ");
                executor.submit(new ServerClientHandler(socket));
            }catch(IOException e){
                break; //In case the serverSocket gets closed
            }
        }
        executor.shutdown();
        serverSocket.close();
    }

}
