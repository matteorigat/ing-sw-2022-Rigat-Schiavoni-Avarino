package it.polimi.ingsw.observer;

import java.util.ArrayList;
import java.util.List;


/**
 * This class represents an observable object, or "data" in the model-view paradigm.
 * It can be subclassed to represent an object that the application wants to have observed.
 * An observable object can have one or more observers. An observer may be any object that
 * implements interface Observer. After an observable instance changes, an application calling
 * the Observable's notifyObservers method causes all of its observers to be notified of the change
 * by a call to their update method.
 * @param <T>
 */
public class Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<>();

    public void addObserver(Observer<T> observer){
        synchronized (observers) {
            observers.add(observer);
        }
    }


    protected void notify(T message){
        synchronized (observers) {
            for(Observer<T> observer : observers){
                observer.update(message);
            }
        }
    }

}
