package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.GUI.controllers.MainPageController;
import it.polimi.ingsw.exceptions.ConnectionClosedException;
import it.polimi.ingsw.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientGUI  {





  /*  private String ip;
    private int port;

    private String nickname = "";

    public ClientGUI(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    private boolean active = true;

    public synchronized boolean isActive(){
        return active;
    }

    public synchronized void setActive(boolean active){
        this.active = active;
    }

    // asyncReadFromSocket

    public Thread asyncReadFromSocket(final ObjectInputStream socketIn){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        Object inputObject = socketIn.readObject();
                        if(inputObject instanceof String){
                            if(((String) inputObject).contains("NICKNAME")){
                                nickname = ((String) inputObject).replace("NICKNAME", "");
                            } else {
                                System.out.println((String)inputObject);
                            }
                        } else if (inputObject instanceof Model){
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

    public Thread asyncWriteToSocket(final Scanner stdin, final ObjectOutputStream socketOut){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        String inputLine = stdin.nextLine();

                        if(inputLine.equals("c") && !nickname.equals("")){
                            String inputLine2 = null;
                            int c;
                            System.out.print("Character card index: ");
                            inputLine = stdin.nextLine();
                            c = (int)inputLine.charAt(0) - 48;
                            if(inputLine.length() > 1)
                                c = c * 10 + (int)inputLine.charAt(1) - 48;

                            if(c == 1){
                                System.out.print("Insert the student color and island index: ");
                                inputLine2 = stdin.nextLine();

                            } else if(c == 3){
                                System.out.print("Insert the island index: ");
                                inputLine2 = stdin.nextLine();

                            } else if(c == 7){
                                System.out.print("Insert the students color in this order... cardStudent1,entranceStudent1,cardStudent2,entranceStudent2,cardStudent3,entranceStudent3  , students 2 and 3 are not necessary: ");
                                inputLine2 = stdin.nextLine();

                            } else if(c == 9){
                                System.out.print("Insert the color: ");
                                inputLine2 = stdin.nextLine();

                            } else if(c == 10){
                                System.out.print("Insert the students color in this order... entranceStudent1,diningStudent1,entranceStudent2,diningStudent2  , students 2 are not necessary: ");
                                inputLine2 = stdin.nextLine();

                            }else if(c == 11){
                                System.out.print("Insert the student color: ");
                                inputLine2 = stdin.nextLine();

                            } else if(c == 12){
                                System.out.print("Insert the student color: ");
                                inputLine2 = stdin.nextLine();
                            }

                            if(inputLine2.equals(null))
                                inputLine = "100," + inputLine;
                            else
                                inputLine = "100," + inputLine + "," + inputLine2;
                        }
                        synchronized (ip){
                            socketOut.writeObject(inputLine);
                            socketOut.flush();
                        }
                    }
                }catch(Exception e){
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

    public void run() throws IOException {
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");

        ObjectOutputStream socketOut = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
        Scanner stdin = new Scanner(System.in);


        try{
            Thread t0 = asyncReadFromSocket(socketIn);
            Thread t1 = asyncWriteToSocket(stdin, socketOut);

            t0.join();
            System.in.close();
            t1.join();
            System.out.println("Connection closed!");

        } catch(InterruptedException | NoSuchElementException | ConnectionClosedException e){
            System.out.println("Connection closed from the client side");
        } finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    } */
}



