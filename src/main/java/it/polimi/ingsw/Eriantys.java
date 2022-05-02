package it.polimi.ingsw;

import it.polimi.ingsw.client.ClientApp;
import it.polimi.ingsw.server.ServerApp;

import java.util.Scanner;

public class Eriantys {

    public static void main(String[] args) {
        System.out.println("WELCOME! What do you want to launch?");
        System.out.println("0. SERVER\n1. CLIENT (CLI INTERFACE)");
        String[] arr = new String[1]; // al main si Passa un array
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("Insert your choice: ");
            input = scanner.nextLine();
        }while (!(input.equals("0") || input.equals("1")));


        switch (input){
            case "0":
                ServerApp.main(null);
                break;
            case "1":
                ClientApp.main(arr);
                break;
            default:
                System.out.println("invalid input!\n");


        }
    }

}
