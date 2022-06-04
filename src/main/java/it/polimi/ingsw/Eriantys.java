package it.polimi.ingsw;

import it.polimi.ingsw.client.ClientAppCLI;
import it.polimi.ingsw.client.ClientAppGUI;
import it.polimi.ingsw.server.ServerApp;
import javafx.application.Application;

import java.util.Scanner;



/**
 * Eriantys class call the executable application depending on the client's decision.
 * it can create 4 differents "App" executions: local,server,client and client with GUI.
 *
 * */

public class Eriantys {


    public static void main(String[] args) {
        System.out.println("WELCOME! What do you want to launch?");
        System.out.println("0. LOCAL\n1. SERVER\n2. CLIENT (CLI INTERFACE)\n3. CLIENT (GUI INTERFACE)");
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("Insert your choice: ");
            input = scanner.nextLine();
        }while (!(input.equals("0") || input.equals("1") || input.equals("2") || input.equals("2") || input.equals("3")));


        switch (input){
            case "0":
                App.main(null);
                break;
            case "1":
                ServerApp.main(null);
                break;
            case "2":
                ClientAppCLI.main(null);
                break;
            case "3":
                ClientAppGUI.main(null);
                break;

            default:
                System.out.println("invalid input!\n");


        }
    }

}
