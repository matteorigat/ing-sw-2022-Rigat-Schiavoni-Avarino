package it.polimi.ingsw.server;

import java.util.Observer;

public interface ClientConnection {

    void closeConnection();

    void addObserver(Observer observer);

    void asyncSend(Object message);

}
