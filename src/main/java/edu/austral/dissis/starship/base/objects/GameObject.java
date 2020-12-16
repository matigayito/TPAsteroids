package edu.austral.dissis.starship.base.objects;

import edu.austral.dissis.starship.base.vector.Vector2;

public abstract class GameObject {
    private Vector2 position;
    private Vector2 direction;
    private boolean alive;

    public GameObject(Vector2 position, Vector2 direction, boolean alive){
        this.position = position;
        this.direction = direction.asUnitary();
        this.alive = alive;
    }

    public abstract GameObject moveForward(float speed);
    public Vector2 getPosition(){
        return position;
    }
    public Vector2 getDirection(){
        return direction;
    }
    public boolean isAlive(){
        return alive;
    }
    public abstract void destroy();

}
