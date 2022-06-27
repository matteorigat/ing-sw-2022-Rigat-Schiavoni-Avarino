package it.polimi.ingsw.server;

import it.polimi.ingsw.observer.Observable;
import it.polimi.ingsw.utils.gameMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * SocketClientConnection handles a connection between client and server, and permits to send and
 * receive messages.
 * @see Runnable
 * @see ClientConnection
 */
public class SocketClientConnection extends Observable<String> implements ClientConnection, Runnable {

    private Socket socket;
    private ObjectOutputStream out;
    private Server server;

    private boolean active = true;

    /**
     * Constructor of SocketClientConnection. It adds the main Server to his attributes too.
     *
     * @param socket  of type Socket - the socket which accepted the client connection.
     * @param server  of type Server - the main server class.
     */
    public SocketClientConnection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    /**
     * Method isActive returns the status of this SocketClientConnection object.
     * @return the active status (type boolean) of this SocketClientConnection object.
     * */
    private synchronized boolean isActive(){
        return active;
    }


    /**
     * Method send is used to send messages to the Client
     * @param message
     */
    public synchronized void send(Object message) {
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch(Exception e){
            System.err.println(e.getMessage());
        }

    }

    /**
     * closeConnection method is used to close the connection with the Client.
     * It closes input and output streams too.
     */
    @Override
    public synchronized void closeConnection() {
        send("Connection closed, another player left the match!");
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
        active = false;
    }


    /** Method close terminates the connection with the client, invoking the server method called "DeregisteringClient",
     * which will remove the active virtual client from the list.*/
    private void close() {
        closeConnection();
        System.out.println("Deregistering client...");
        server.deregisterConnection(this);
        System.out.println("Done!");
    }

    @Override
    public void asyncSend(final Object message){
        new Thread(() -> send(message)).start();
    }


    /** Method run is the overriding runnable class method, which is called on a new client connection.
     * It creates an input/output stream.
     * Every new Client connected will sign in by writing his username and if he's the first player in the match he will choose
     * the gamemode and the number of players
     *
     * @see Runnable #run()
     *
     */
    @Override
    public void run() {
        String name;
        int numPlayers;
        Boolean expertMode;
        try{
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            send(gameMessage.eriantys);

            send("Welcome! What is your nickname?");
            Object read;
            boolean errorName;
            do {
                errorName = false;
                read = in.readObject();
                name = (String) read;
                for (String s : server.getNicknames()) {
                    if (s.equals(name)) {
                        send("Another player already chosen this nickname, try with a new one!");
                        errorName = true;
                    }
                }
                if(name.equals("")) {
                    send("Write a valid name! Please,try again\nWhat is your name?");
                    errorName = true;
                }
            } while (errorName);
            send("NICKNAME" + name);//IMPORTANTE non modificare la scritta NICKNAME, serve per riconoscimento lato client
            if(server.isChooseMode()){
                send("The first player is choosing the game mode, please wait the beginning of the game");
                while (server.getWaitingConnection().size() == 1 && server.isChooseMode()){
                    Thread.sleep(250);
                }
            }
            server.lobby(this, name);
            synchronized (server) {
                if (server.getWaitingConnection().size() == 1){
                    server.setChooseMode(true);
                    numPlayers = 0;
                    do{
                        send("How many players?");
                        read = in.readObject();
                        if(!read.equals("2") && !read.equals("3")){
                            send("Error, please write a correct number of players");
                        }
                    } while(!read.equals("2") && !read.equals("3"));
                    numPlayers = Integer.parseInt((String) read);
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

