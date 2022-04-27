package it.polimi.ingsw.server;

import java.io.IOException;

public class ServerApp {

        public static void main( String[] args )
        {
            Server server = new Server(1337);
            try {
                server.startServer();
            } catch(IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }


