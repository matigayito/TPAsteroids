package edu.austral.dissis.starship.Observer;

public interface PointsObservable {

    void addObserver(PointsObserver observer);
    void removeObserver(PointsObserver observer);
    void addPoints(int points, String tag);
}
