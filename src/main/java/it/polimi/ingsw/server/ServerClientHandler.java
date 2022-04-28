package it.polimi.ingsw.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;
import java.util.Observable;
import java.util.Scanner;

public class ServerClientHandler extends Observable implements ClientConnection , Runnable {
    private Socket socket;
    private Server server;
    private ObjectOutputStream out;
    public ServerClientHandler(Socket socket,Server server){
        this.server = server;
        this.socket = socket;
    }
    private boolean active = true;

    private synchronized void send(Object message) {
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }

    }


    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            while (true){
                String name = in.nextLine();
                System.out.println("ho ricevuto : " + name);
                String answare = name.toUpperCase(Locale.ROOT);
                System.out.println("[3]  -Rispondo con :" +answare);
                out.writeObject(answare + "\n");
                server.lobby(this, name);
                if(name.equals("quit")){
                    break;
                } else {
                    out.writeObject("Received: " + name);
                    out.flush();
                }
            }
            //close connections
            in.close();
            out.close();
            socket.close();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
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
    public void asyncSend(final Object message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                send(message);
            }
        }).start();
    }

    private void close() {
        closeConnection();
    }
}


