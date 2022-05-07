package it.polimi.ingsw.client;


import it.polimi.ingsw.client.Client;

import java.io.IOException;
import java.util.Scanner;

public class ClientApp {


    public static void main(String[] args) {
        System.out.println("IP    or digit one casual letter for local ip");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        if(s.length() == 1)
            s = "127.0.0.1";
        Client client = new Client("192.168.100.6",1337); //192.168.100.10
        try {
            client.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
