package it.polimi.ingsw.Server;

import sun.jvm.hotspot.utilities.Observable;
import sun.jvm.hotspot.utilities.Observer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientConnection extends Observable implements ClientConnection, Runnable{

    private Socket socket;
    private ObjectOutputStream out;
    private Server server;
    private boolean active = true;

    public SocketClientConnection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {

    }

    @Override
    public synchronized void closeConnection() {
        send("Connection closed!");
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
        active = false;
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void asyncSend(Object message) {

    }


    private synchronized void send(Object message) {
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }

    }

    private void close() {
        closeConnection();
        System.out.println("Deregistering client...");
        //creare metodo in server che deregistri il client
        System.out.println("Done!");
    }
}
