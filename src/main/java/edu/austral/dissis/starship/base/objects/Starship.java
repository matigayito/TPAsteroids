package edu.austral.dissis.starship.base.objects;

import edu.austral.dissis.starship.base.vector.Vector2;

public class Starship extends GameObject{
    private final Vector2 position;
    private final Vector2 direction;
    private boolean alive;
    private String tag;

    public Starship(Vector2 position, Vector2 direction, boolean alive, String tag) {
        super(position, direction, alive);
        this.position = position;
        this.direction = direction.asUnitary();
        this.alive = alive;
        this.tag = tag;
    }

    public Starship rotate(float angle) {
        return new Starship(position, direction.rotate(angle), alive, tag);
    }

    public Starship moveForward(float speed) {
        return new Starship(position.add(direction.multiply(speed)), direction, alive, tag);
    }

    public Starship moveBackwards(float speed) {
        return new Starship(position.subtract(direction.multiply(speed)), direction, alive, tag);
    }

    public void destroy(){
        alive = false;
    }

    public String getTag() {
        return tag;
    }
}
