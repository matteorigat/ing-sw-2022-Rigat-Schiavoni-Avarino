package it.polimi.ingsw;

import it.polimi.ingsw.server.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;




public class Connection implements Runnable {

        private Socket socket;
        private Scanner in;
        private PrintWriter out;
        private Server server;
        private String name;
        private int numOfPlayers;
        private Boolean gameMode;
        private boolean active = true;

        public Connection(Socket socket, Server server) {
            this.socket = socket;
            this.server = server;
        }

        private synchronized boolean isActive() {
            return active;
        }

        public void send(String message) {
            out.println(message);
            out.flush();
        }

        public void asyncSend(final String message) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    send(message);
                }
            }).start();
        }

        public synchronized void closeConnection() {
            send("Connection closed from the server side");
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            active = false;
        }

        private void close() {
            closeConnection();
            System.out.println("Deregistering client...");
            server.deregisterConnection(this);
            System.out.println("Done!");
        }

        @Override
        public void run() {
            try {
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream());
                send("Welcome! What's your name?");
                name = in.nextLine();
                System.out.println("[1] nome ricevuto: "+ name);
                send("select how many players");
                numOfPlayers = in.nextInt();
                System.out.println("[2] numero di giocatori ricevuto: " + numOfPlayers);
                gameMode = true;



                server.lobby(this, name, numOfPlayers,gameMode);
                while (isActive()) {

                    String read = in.nextLine();

                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } finally {
                close();
            }
        }

    }


