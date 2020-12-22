package edu.austral.dissis.starship.base.objects;

import edu.austral.dissis.starship.base.vector.Vector2;

public class Starship extends GameObject{
    private final Vector2 position;
    private final Vector2 direction;
    private boolean alive;
    private String tag;
    private int posy;
    private int posx;

    public int getPosy() {
        return posy;
    }

    public int getPosx() {
        return posx;
    }

    public Starship(Vector2 position, Vector2 direction, boolean alive, String tag, int x, int y) {
        super(position, direction, alive);
        this.position = position;
        this.direction = direction.asUnitary();
        this.alive = alive;
        this.tag = tag;
        posy = y;
        posx = x;
    }

    public Starship rotate(float angle) {
        return new Starship(position, direction.rotate(angle), alive, tag, posx, posy);
    }

    public Starship moveForward(float speed) {
        return new Starship(position.add(direction.multiply(speed)), direction, alive, tag, posx, posy);
    }

    public Starship moveBackwards(float speed) {
        return new Starship(position.subtract(direction.multiply(speed)), direction, alive, tag, posx, posy);
    }

    public void destroy(){
        alive = false;
    }

    public String getTag() {
        return tag;
    }
}
