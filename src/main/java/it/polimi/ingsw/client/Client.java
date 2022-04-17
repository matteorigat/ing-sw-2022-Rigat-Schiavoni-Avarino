package it.polimi.ingsw.client;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    private String ip;
    private int port;
    BufferedReader tastiera;


    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void run() throws IOException {

        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");
        Scanner socketIn = new Scanner(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        Scanner stdin = new Scanner(System.in);

        try{
            while(true) {
                System.out.println("[1] digitare nome");
                String inputLine = stdin.nextLine(); // giocatore scrive nome
                System.out.println("[2] nome inviato...");
                socketOut.println(inputLine);// il nome viene inviato
                System.out.println("[3] digitare numero giocatori: ");
                Integer inputNumOfPlayers = stdin.nextInt();// giocatore scrive numero di players nella partita
                System.out.println("[4] numero giocatori inviato...");
                socketOut.println(inputNumOfPlayers);
                socketOut.flush();
                String socketLine = socketIn.nextLine();
                System.out.println(socketLine);
            }
        } catch(NoSuchElementException e){
            System.out.println("Connection closed");
        } finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }


}
