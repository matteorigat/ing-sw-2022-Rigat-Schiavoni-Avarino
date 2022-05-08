package it.polimi.ingsw.server;

import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.utils.gameMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientConnection extends Observable<String> implements ClientConnection, Runnable {

    private Socket socket;
    private ObjectOutputStream out;
    private Server server;

    private boolean active = true;

    public SocketClientConnection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    private synchronized boolean isActive(){
        return active;
    }

    public synchronized void send(Object message) {
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch(Exception e){
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

    private void close() {
        closeConnection();
        System.out.println("Deregistering client...");
        server.deregisterConnection(this);
        System.out.println("Done!");
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

    @Override
    public void run() {
        String name;
        int numPlayers;
        Boolean expertMode;
        try{
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            send(gameMessage.eriantys);

            send("Welcome!\nWhat is your name?");
            Object read;
            boolean errorName;
            do {
                errorName = false;
                read = in.readObject();
                name = (String) read;
                for (String s : server.getNicknames()) {
                    if (s.equals(name)) {
                        send("Another player already chosed this name, try with a new one!");
                        errorName = true;
                    }
                }
                if(name.equals("")) {
                    send("Write a valid name! Please,try again\nWhat is your name?");
                    errorName = true;
                }
            } while (errorName);
            send("NICKNAME" + name);//IMPORTANTE non modificare la scritta NICKNAME, serve per riconoscimento lato client
            if(server.isChooseMode())
                send("The first player is choosing the game mode, please wait the beginning of the game");
            server.lobby(this, name);
            synchronized (server) {
                if (server.getWaitingConnection().size() == 1){
                    server.setChooseMode(true);
                    numPlayers = 0;
                    while(numPlayers != 2 && numPlayers != 3){
                        send("How many players?");
                        read = in.readObject();
                        if(read.equals("")){
                            send("Error, please write a correct number of players");
                        }else{
                            numPlayers = Integer.parseInt((String) read);
                            if(numPlayers != 2 & numPlayers != 3)
                                send("Can't create a match with " + numPlayers + " player\n");
                        }
                    }
                    boolean wrongInput;
                    String read1;
                    do {
                        wrongInput = true;
                        send("Expert mode? y or n");
                        read = in.readObject();
                        read1 = (String)read;
                        if(read1.equals("y") || read1.equals("n"))
                            wrongInput = false;

                        if (wrongInput)
                            send("Error! Please write y or n");

                    }while (wrongInput);

                    if(read1.equals("y"))
                        expertMode = true;
                    else
                        expertMode = false;
                    server.setParameters(numPlayers, expertMode);
                    server.setChooseMode(false);
                }
                if(server.getWaitingConnection().size() < server.getNumPlayers() && server.getWaitingConnection().size() != 0)
                    send("\nWaiting for another player\n");
            }

            while(isActive()){

                read = in.readObject();
                notify((String)read);
            }
        } catch (Exception e) {
            System.err.println("Error! " + e.getMessage());
        } finally{
            close();
        }

    }
}

