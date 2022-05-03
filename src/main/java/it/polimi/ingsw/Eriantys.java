package it.polimi.ingsw;

import it.polimi.ingsw.client.ClientApp;
import it.polimi.ingsw.server.ServerApp;

import java.util.Scanner;

public class Eriantys {

    public static void main(String[] args) {
        System.out.println("WELCOME! What do you want to launch?");
        System.out.println("0. LOCAL\n1. SERVER\n2. CLIENT (CLI INTERFACE)");
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("Insert your choice: ");
            input = scanner.nextLine();
        }while (!(input.equals("0") || input.equals("1") || input.equals("2")));


        switch (input){
            case "0":
                App.main(null);
                break;
            case "1":
                ServerApp.main(null);
                break;
            case "2":
                ClientApp.main(null);
                break;

            default:
                System.out.println("invalid input!\n");


        }
    }

}
