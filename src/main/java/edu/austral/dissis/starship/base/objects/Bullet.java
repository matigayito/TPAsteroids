package edu.austral.dissis.starship.base.objects;

import edu.austral.dissis.starship.base.vector.Vector2;

public class Bullet extends GameObject {
    private final Vector2 position;
    private final Vector2 direction;
    private boolean alive;
    String tag;

    public Bullet(Vector2 position, Vector2 direction, boolean alive, String name) {
        super(position, direction, alive);
        this.position = position;
        this.direction = direction.asUnitary();
        this.alive = alive;
        tag = name;
    }

    public Bullet moveForward(float speed) {
        return new Bullet(position.add(direction.multiply(speed)), direction, alive, tag);
    }

    public void destroy(){
        alive = false;
    }

    public String getTag() {
        return tag;
    }
}
