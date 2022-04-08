package it.polimi.ingsw.Server;


import sun.jvm.hotspot.utilities.Observer;

public interface ClientConnection{

        void closeConnection();

        void addObserver(Observer observer);

        void asyncSend(Object message);
    }

