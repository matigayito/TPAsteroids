package edu.austral.dissis.starship.Collisionables;

import edu.austral.dissis.starship.Observer.PointsObservable;
import edu.austral.dissis.starship.Observer.PointsObserver;
import edu.austral.dissis.starship.Player.Player;
import edu.austral.dissis.starship.base.objects.Bullet;
import edu.austral.dissis.starship.base.objects.Starship;
import edu.austral.dissis.starship.base.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

public class BulletCollisionable extends SquareCollisionable implements PointsObservable {
    Bullet bullet;
    List<PointsObserver> observers;

    public BulletCollisionable(int size, float angle, Vector2 position, Bullet bullet, List<PointsObserver> players) {
        super(size, angle, position);
        this.bullet = bullet;
        observers = players;

    }

    @Override
    public void collisionedWith(SquareCollisionable collisionable) {
        collisionable.collisionedWithBullet(this);
    }

    @Override
    void collisionedWithBullet(BulletCollisionable collisionable) {
    }

    @Override
    void collisionedWithStarship(StarshipCollisionable collisionable) {
        addPoints(100, bullet.getTag());
        collisionable.starship.destroy();
        bullet.destroy();
    }

    @Override
    void collisionedWithAsteroid(AsteroidCollisionable collisionable) {
        addPoints(50, bullet.getTag());
        collisionable.asteroid.destroy();
        bullet.destroy();
    }

    @Override
    public void addObserver(PointsObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(PointsObserver observer) {
        observers.add(observer);
    }

    @Override
    public void addPoints(int points, String tag) {
        for (int i = 0; i < observers.size(); i++) {
            if (observers.get(i).getTag().equals(tag)){
                observers.get(i).addPoints(points);
            }
        }
    }
}
