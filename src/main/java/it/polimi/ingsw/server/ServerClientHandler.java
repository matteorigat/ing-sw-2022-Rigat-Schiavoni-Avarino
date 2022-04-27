package it.polimi.ingsw.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;

public class ServerClientHandler implements Runnable {
    private Socket socket;

    public ServerClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (true){
                String line = in.nextLine();
                System.out.println("ho ricevuto : " + line);
                String answare = line.toUpperCase(Locale.ROOT);
                System.out.println("[5]  -Rispondo con :" +answare);
                out.println(answare + "\n");

                if(line.equals("quit")){
                    break;
                } else {
                    out.println("Received: " + line);
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
}


