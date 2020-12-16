package edu.austral.dissis.starship.Collisionables;

import edu.austral.dissis.starship.base.objects.Asteroid;
import edu.austral.dissis.starship.base.vector.Vector2;

public class AsteroidCollisionable extends SquareCollisionable
{
    Asteroid asteroid;

    public AsteroidCollisionable(int size, float angle, Vector2 position, Asteroid asteroid) {
        super(size, angle, position);
        this.asteroid=asteroid;
    }

    @Override
    public void collisionedWith(SquareCollisionable collisionable){
        collisionable.collisionedWithAsteroid(this);
    }

    @Override
    void collisionedWithBullet(BulletCollisionable collisionable) {
        collisionable.bullet.destroy();
        asteroid.destroy();
    }

    @Override
    void collisionedWithStarship(StarshipCollisionable collisionable) {
        collisionable.starship.destroy();
        asteroid.destroy();
    }

    @Override
    void collisionedWithAsteroid(AsteroidCollisionable collisionable) {
    }
}
