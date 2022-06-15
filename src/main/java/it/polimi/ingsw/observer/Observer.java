package it.polimi.ingsw.observer;


/**
 * Observer Interface
 * @param <T>
 */
public interface Observer<T> {

    void update(T message);

}
