package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.ClientCLI;

import java.io.IOException;
import java.util.Scanner;

public class ClientAppCLI {


    public static void main(String[] args) {
        System.out.println("IP    or digit one casual letter for local ip");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        if(!s.contains("."))
            s = "127.0.0.1";
        ClientCLI clientCLI = new ClientCLI(s,1337); //192.168.100.10
        try {
            clientCLI.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
