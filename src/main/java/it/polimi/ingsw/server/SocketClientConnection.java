package it.polimi.ingsw.server;

import it.polimi.ingsw.observer.Observable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
        } catch(IOException e){
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
        Scanner in;
        String name;
        int numPlayers;
        Boolean expertMode;
        try{
            in = new Scanner(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            send("\n███████╗██████╗░██╗░█████╗░███╗░░██╗████████╗██╗░░░██╗░██████╗");
            send("██╔════╝██╔══██╗██║██╔══██╗████╗░██║╚══██╔══╝╚██╗░██╔╝██╔════╝");
            send("█████╗░░██████╔╝██║███████║██╔██╗██║░░░██║░░░░╚████╔╝░╚█████╗░");
            send("██╔══╝░░██╔══██╗██║██╔══██║██║╚████║░░░██║░░░░░╚██╔╝░░░╚═══██╗");
            send("███████╗██║░░██║██║██║░░██║██║░╚███║░░░██║░░░░░░██║░░░██████╔╝");
            send("╚══════╝╚═╝░░╚═╝╚═╝╚═╝░░╚═╝╚═╝░░╚══╝░░░╚═╝░░░░░░╚═╝░░░╚═════╝░\n");

            send("Welcome!\nWhat is your name?");
            String read;
            boolean duplicateName;
            do {
                duplicateName = false;
                read = in.nextLine();
                name = read;
                for(String s: server.getNicknames()){
                    if(s.equals(name)){
                        send("Another player already chosed this name, try with a new one!\n");
                        duplicateName = true;
                    }
                }
            } while (duplicateName);
            send("NICKNAME" + name);
            if(server.isChooseMode())
                send("The first player is choosing the game mode, please wait the beginning of the game\n");
            server.lobby(this, name);
            synchronized (server) {
                if (server.getWaitingConnection().size() == 1) {
                    server.setChooseMode(true);
                    numPlayers = 0;
                    while(numPlayers != 2 & numPlayers != 3){
                        send("How many players?");
                        read = in.nextLine();
                        numPlayers = Integer.parseInt(read);
                        if(numPlayers != 2 & numPlayers != 3){
                            send("Can't create a match with " + numPlayers + " players \n " + "try again" );
                        }
                    }
                    send("Expert mode? y or n");
                    read = in.nextLine();
                    if (read.equals("y"))
                        expertMode = true;
                    else expertMode = false;
                    server.setParameters(numPlayers, expertMode);
                    server.setChooseMode(false);
                }
                if(server.getWaitingConnection().size() < server.getNumPlayers() && server.getWaitingConnection().size() != 0)
                    send("Waiting for another player\n");
            }

            while(isActive()){
                read = in.nextLine();
                notify(read);
            }
        } catch (IOException | NoSuchElementException e) {
            System.err.println("Error! " + e.getMessage());
        }finally{
            close();
        }

    }
}

