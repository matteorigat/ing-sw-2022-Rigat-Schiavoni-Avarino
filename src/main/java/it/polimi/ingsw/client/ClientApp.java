package it.polimi.ingsw.client;


import it.polimi.ingsw.client.Client;

import java.io.IOException;
import java.util.Scanner;

public class ClientApp {


    public static void main(String[] args) {
        //System.out.println("IP?");
        //Scanner input = new Scanner(System.in);
        //String s = input.nextLine();
        System.out.println("IP: ");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        Client client = new Client(s,1337); //192.168.100.10
        try {
            client.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
