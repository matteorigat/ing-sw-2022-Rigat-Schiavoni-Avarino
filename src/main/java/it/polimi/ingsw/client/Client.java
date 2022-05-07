package it.polimi.ingsw.client;

import it.polimi.ingsw.exceptions.ConnectionClosedException;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.server.Pong;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    private String ip;
    private int port;

    private String nickname = "";


    public Client(String ip, int port){
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
                        } else if(inputObject instanceof Pong){

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

                        if(inputLine.equals("c") && !nickname.equals("")){ //va cambiato
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

                            } else if(c == 11){
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
            Thread t2 = pinging(socketOut);

            t2.join();
            System.out.println("Connection closed t2");
            t0.join();
            System.out.println("Connection closed t0");

            t1.interrupt();
            System.out.println("interrputed t1");
            System.in.close();
            /*socketIn.close();
            System.out.println("socketin");
            socketOut.close();
            System.out.println("socketout");
            socket.close();
            System.out.println("socket");
            */
            t1.stop();
            t1.join();
            System.out.println("Connection closed t1");

        } catch(InterruptedException | NoSuchElementException | ConnectionClosedException e){
            System.out.println("Connection closed from the client side");
        } finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }

    public Thread pinging(final ObjectOutputStream socketOut) throws RuntimeException{
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {

                System.out.println("Ping started!\n");
                while(true) {
                    if (!isActive()) throw new ConnectionClosedException();
                    try {
                        Thread.sleep(1000);
                        synchronized (ip) {

                        //    System.out.println("Sending ping!");
                            socketOut.writeObject(new Ping());
                            socketOut.flush();

                         //   System.out.println("Ping sent|");
                        }
                    }
                         catch (Exception e) {
                            setActive(false);
                            System.out.println("Connection closed!");
                            throw new ConnectionClosedException();


                        //    throw new RuntimeException("Connection closed. Sorry!");

                        }
                    }



            }
        });
        t.start();
        return t;
    }




}



