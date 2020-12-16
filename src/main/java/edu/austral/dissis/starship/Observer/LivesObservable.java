package edu.austral.dissis.starship.Observer;

public interface LivesObservable {

    void addObserver(LivesObserver observer);
    void removeObserver(LivesObserver observer);
    void looseLife(String tag);
}
