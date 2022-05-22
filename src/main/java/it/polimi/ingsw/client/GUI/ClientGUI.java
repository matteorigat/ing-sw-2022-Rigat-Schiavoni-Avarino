package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.exceptions.ConnectionClosedException;
import it.polimi.ingsw.model.Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

public class ClientGUI implements Runnable {

    private String ip;
    private Integer port;

    private ObjectOutputStream socketOut;

    private ClientAppGUI gui;

    private String nickname = "";
    private boolean loading = false;
    private boolean firstPlayer = false;
    private boolean startgame = false;

    public ClientGUI(String ip, int port, ClientAppGUI gui){
        this.ip = ip;
        this.port = port;
        this.gui = gui;
    }

    private boolean active = true;

    public void setNickname(String nickname) {
        this.nickname = nickname;
        System.out.println("Set: " + this.nickname);
    }

    public boolean isLoading() {
        return loading;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public boolean isStartgame() {
        return startgame;
    }

    public synchronized boolean isActive(){
        return active;
    }

    public synchronized void setActive(boolean active){
        this.active = active;
    }

    // asyncReadFromSocket

    public Thread asyncReadFromSocket(final ObjectInputStream socketIn, final ObjectOutputStream socketOut){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        Object inputObject = socketIn.readObject();
                        if(inputObject instanceof String){
                            System.out.println((String) inputObject);
                            if(((String) inputObject).contains("Welcome") || ((String) inputObject).contains("valid")){
                                System.out.println("Sending: " + nickname);
                                asyncWriteToSocket(nickname);
                            } else if(((String) inputObject).contains("chosen")){
                                asyncWriteToSocket(nickname);
                            } else if (((String) inputObject).contains("players")){
                                firstPlayer = true;
                            } else if (((String) inputObject).contains("beginning")){
                                loading = true;
                            } else if (((String) inputObject).contains("opponent")){
                                startgame = true;
                            }
                        } else if (inputObject instanceof Model){
                            startgame = true;
                            ((Model)inputObject).print(nickname);
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                } catch (Exception e){
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

    public Thread asyncWriteToSocket(String message){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (ip){
                        socketOut.writeObject(message);
                        socketOut.flush();
                    }
                }catch(Exception e){
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(ip, port);
            System.out.println("Connection established");

            ObjectOutputStream socketOut = new ObjectOutputStream(socket.getOutputStream());
            this.socketOut = socketOut;
            ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());

            try{
                Thread t0 = asyncReadFromSocket(socketIn, socketOut);

                t0.join();
                System.in.close();
                System.out.println("Connection closed!");

            } catch(InterruptedException | NoSuchElementException | ConnectionClosedException e){
                System.out.println("Connection closed from the client side");
            } finally {
                socketIn.close();
                socketOut.close();
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



