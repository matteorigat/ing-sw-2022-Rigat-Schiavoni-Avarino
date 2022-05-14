package it.polimi.ingsw.server;

import java.io.IOException;

/**
 * The main class of the server. It simply creates a new server class, adding a server socket to
 * an executor.
 *
 */
public class ServerApp
{
    public static void main( String[] args )
    {
        Server server;
        try {
            server = new Server();
            server.run();
        } catch (IOException e) {
            System.err.println("Impossible to initialize the server: " + e.getMessage() + "!");
        }
    }
}
