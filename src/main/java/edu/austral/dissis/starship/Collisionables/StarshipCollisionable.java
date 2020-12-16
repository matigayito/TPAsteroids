package edu.austral.dissis.starship.Collisionables;

import edu.austral.dissis.starship.Observer.LivesObservable;
import edu.austral.dissis.starship.Observer.LivesObserver;
import edu.austral.dissis.starship.Observer.PointsObserver;
import edu.austral.dissis.starship.base.objects.Starship;
import edu.austral.dissis.starship.base.vector.Vector2;

import java.util.List;

public class StarshipCollisionable extends SquareCollisionable implements LivesObservable {
    Starship starship;
    List<LivesObserver> observers;

    public StarshipCollisionable(int size, float angle, Vector2 position, Starship s, List<LivesObserver> list){
        super(size ,angle,position);
        starship = s;
        observers = list;
    }

    public void collisionedWith(SquareCollisionable collisionable){
        collisionable.collisionedWithStarship(this);
    }

    @Override
    void collisionedWithBullet(BulletCollisionable collisionable) {
        starship.destroy();
        collisionable.bullet.destroy();
        looseLife(starship.getTag());
    }

    @Override
    void collisionedWithStarship(StarshipCollisionable collisionable) {
        starship.destroy();
        collisionable.starship.destroy();
        looseLife(starship.getTag());
    }

    @Override
    void collisionedWithAsteroid(AsteroidCollisionable collisionable) {
        this.starship.destroy();
        collisionable.asteroid.destroy();
        looseLife(starship.getTag());

    }

    @Override
    public void addObserver(LivesObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(LivesObserver observer) {
        observers.add(observer);
    }

    @Override
    public void looseLife(String tag) {
        for (int i = 0; i < observers.size(); i++) {
            if (observers.get(i).getTag().equals(tag)){
                observers.get(i).looseLife();
            }
        }
    }
}
