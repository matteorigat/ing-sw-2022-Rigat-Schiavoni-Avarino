package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.model.Model;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

/**
 * Client class GUI it's the main class for graphical user interface
 */
public class ClientGUI implements Runnable {

    private String ip;
    private Integer port;

    private ObjectOutputStream socketOut;

    private ClientAppGUI gui;

    private String nickname = "";
    private String message = "Connection closed from server side";
    private boolean firstModel = true;


    /**
     * ClientGUI constructor
     * @param ip
     * @param port
     * @param gui
     */
    public ClientGUI(String ip, int port, ClientAppGUI gui){
        this.ip = ip;
        this.port = port;
        this.gui = gui;
    }

    private boolean active = true;

    /**
     * setNickname method sets the nickname of the player
     * @param nickname
     */
    public void setNickname(String nickname) {
        asyncWriteToSocket(nickname);
        this.nickname = nickname;
    }

    /**
     * isActive method returns active (boolean parameter)
     * @return active
     */
    public synchronized boolean isActive(){
        return active;
    }

    /**
     * setActive method sets active parameter
     * @param active
     */
    public synchronized void setActive(boolean active){
        this.active = active;
    }

    /**
     * asyncReadFromSocket method creates a thread which receives a message from the socket in asynchronized way
     *
     * @param socketIn
     * @return thread
     */
    // asyncReadFromSocket
    public Thread asyncReadFromSocket(final ObjectInputStream socketIn, final ObjectOutputStream socketOut){
        Thread t = new Thread(() -> {
            try {
                while (isActive()) {
                    Object inputObject = socketIn.readObject();
                    if(inputObject instanceof String){
                        System.out.println((String) inputObject);
                        if (((String) inputObject).contains("Write a valid name!") || ((String) inputObject).contains("Another player already chosen this nickname")){
                            Platform.runLater(()-> gui.getNicknameController().clearNickname());
                        }
                        else if (((String) inputObject).contains("How many players?")){
                            Platform.runLater(()-> gui.changeStage("FirstPlayer"));
                        }
                        else if (((String) inputObject).contains("please wait the beginning of the game") || ((String) inputObject).contains("Waiting for another player")){
                            if(!gui.getCurrentFXML().equals("Loading"))
                                Platform.runLater(()-> gui.changeStage("Loading"));
                        }
                        else if (((String) inputObject).contains("opponent")){
                            gui.getGameboardController().setNickname(nickname);
                        }
                        else if (((String) inputObject).contains("left")){
                            message = (String) inputObject;
                        }
                    } else if (inputObject instanceof Model){
                        Platform.runLater(()-> gui.getGameboardController().setModel((Model)inputObject));
                        if(firstModel){
                            Platform.runLater(()-> gui.changeStage("GameBoard"));
                            firstModel = false;
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            } catch (Exception e){
                setActive(false);
            }
        });
        t.start();
        return t;
    }


    /**
     * asyncWriteToSocket method creates a thread which sends a message to the socket in asynchronized way
     * @param message
     * @return thread
     */
    public Thread asyncWriteToSocket(String message){
        Thread t = new Thread(() -> {
            try {
                synchronized (ip){
                    socketOut.writeObject(message);
                    socketOut.flush();
                }
            }catch(Exception e){
                setActive(false);
            }
        });
        t.start();
        return t;
    }

    /**
     * run method of Client
     */
    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip, port);
            Platform.runLater(()-> gui.changeStage("Nickname"));
            System.out.println("Connection established");

            ObjectOutputStream socketOut = new ObjectOutputStream(socket.getOutputStream());
            this.socketOut = socketOut;
            ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());

            try{
                Thread t0 = asyncReadFromSocket(socketIn, socketOut);

                t0.join();
                System.in.close();

            } catch(InterruptedException | NoSuchElementException e){
                System.out.println("Exception connection closed");
            } finally {
                Platform.runLater(()-> gui.closeConnection(message));
                socketIn.close();
                socketOut.close();
                socket.close();
            }
        } catch (IOException e) {
            Platform.runLater(()-> gui.getMainMenuController().clearIpPort());
            throw new RuntimeException(e);
        }

    }

    /**
     * getNickname method returns nickname
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }
}



