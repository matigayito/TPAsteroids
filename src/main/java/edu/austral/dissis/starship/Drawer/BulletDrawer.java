package edu.austral.dissis.starship.Drawer;

import edu.austral.dissis.starship.Collisionables.BulletCollisionable;
import edu.austral.dissis.starship.Collisionables.SquareCollisionable;
import edu.austral.dissis.starship.Observer.PointsObserver;
import edu.austral.dissis.starship.Player.Player;
import edu.austral.dissis.starship.base.objects.Bullet;
import edu.austral.dissis.starship.base.vector.Vector2;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.util.List;

public class BulletDrawer {
    private static final float IMAGE_SIZE = 200;
    public static final int SQUARE_SIZE = 10;
    private final List<PointsObserver> players;

    public BulletDrawer(List<PointsObserver> players) {
        this.players = players;
    }

    private float getImageCenter() {
        return IMAGE_SIZE / -2f;
    }

    public void draw(PGraphics graphics, Bullet bullet) {
        final Vector2 position = bullet.getPosition();
        final float angle = calculateRotation(bullet);

        graphics.pushMatrix();

        graphics.translate(position.getX(), position.getY());
        graphics.rotate(angle);

//        graphics.image(image, getImageCenter(), getImageCenter());
        graphics.fill(0, 0, 0);
        graphics.rect(SQUARE_SIZE / -2f, SQUARE_SIZE / -2f, SQUARE_SIZE, SQUARE_SIZE);


        graphics.popMatrix();

        graphics.fill(0, 255, 0);
    }

    private float calculateRotation(Bullet bullet) {
        return bullet.getDirection().rotate(PConstants.PI / 2).getAngle();
    }

    public BulletCollisionable getCollisionable(Bullet bullet) {
        return new BulletCollisionable(
                SQUARE_SIZE,
                calculateRotation(bullet),
                bullet.getPosition(),
                bullet,
                players
        );
    }
}
