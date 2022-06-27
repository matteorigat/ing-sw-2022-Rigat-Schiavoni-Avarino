package it.polimi.ingsw.server;

import it.polimi.ingsw.observer.Observer;
/**
 * ClientConnection class defines an interface representing a connection with a client.
 */


public interface ClientConnection{

    void closeConnection();

    void addObserver(Observer<String> observer);

    void asyncSend(Object message);
}
