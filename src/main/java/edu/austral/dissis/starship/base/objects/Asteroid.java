package edu.austral.dissis.starship.base.objects;

import edu.austral.dissis.starship.base.vector.Vector2;

public class Asteroid extends GameObject {
    private final Vector2 position;
    private final Vector2 direction;
    private boolean alive;

    public Asteroid(Vector2 position, Vector2 direction, boolean alive) {
        super(position, direction, alive);
        this.position = position;
        this.direction = direction.asUnitary();
        this.alive = alive;
    }

    public Asteroid moveForward(float speed) {
        return new Asteroid(position.add(direction.multiply(speed)), direction, alive);
    }

    public void destroy(){
        alive = false;
    }
}
