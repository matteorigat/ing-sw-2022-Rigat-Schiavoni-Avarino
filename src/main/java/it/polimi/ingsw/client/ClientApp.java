package it.polimi.ingsw.client;


import it.polimi.ingsw.client.Client;

import java.io.IOException;
import java.util.Scanner;

public class ClientApp {


    public static void main(String[] args) {
        System.out.println("IP?");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        Client client = new Client(s,1337);
        try {
            client.startClient();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
