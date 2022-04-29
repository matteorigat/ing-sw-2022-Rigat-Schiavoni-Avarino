package it.polimi.ingsw.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Locale;
import java.util.Observable;
import java.util.Scanner;

public class ServerClientHandler extends Observable implements ClientConnection , Runnable {
    private Socket socket;
    private Server server;
    private Writer out;
    public ServerClientHandler(Socket socket,Server server){
        this.server = server;
        this.socket = socket;
    }
    private boolean active = true;

    private synchronized void send(String message) {
        try {
            //out.reset();
            out.write(message);
            out.flush();
        } catch(IOException e){
            System.err.println(e.getMessage());
        }

    }


    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            while (true){
                String name = in.nextLine();
                System.out.println("ho ricevuto : " + name);
                String answare = name.toUpperCase(Locale.ROOT);
                System.out.println("[3]  -Rispondo con :" +answare);
                out.write(answare + "\n");
                server.lobby(this, name);
                if(name.equals("quit")){
                    break;
                } else {
                    out.write("Received: " + name);
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
    public void asyncSend(Object message) {

    }

    //@Override
    public void asyncSend(final String message){
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


