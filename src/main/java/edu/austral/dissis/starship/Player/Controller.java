package edu.austral.dissis.starship.Player;

import edu.austral.dissis.starship.base.objects.Bullet;
import edu.austral.dissis.starship.base.objects.Starship;
import processing.core.PConstants;

import java.util.List;
import java.util.Set;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;

public class Controller {
    private int up;
    private int down;
    private int left;
    private int right;
    private int shoot;
    private String tag;

    public Controller(int up, int down, int left, int right, int shoot, String tag) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.shoot = shoot;
        this.tag = tag;
    }

    public Starship keyHandle(Set<Integer> keySet, Starship starship, List<Bullet> bullets, float timeSinceLastDraw){
        Starship newStarship = starship;
        if (keySet.contains(up)) {
            newStarship = newStarship.moveForward(5);
        }

        if (keySet.contains(down)) {
            newStarship = newStarship.moveBackwards(2);
        }

        if (keySet.contains(left)) {
            newStarship = newStarship.rotate(-1 * PConstants.PI / 60);
        }

        if (keySet.contains(right)) {
            newStarship = newStarship.rotate(PConstants.PI / 60);
        }

        if (keySet.contains(shoot)) {
            if (timeSinceLastDraw > 20 && bullets.size() < 10) {
                bullets.add(new Bullet(starship.getPosition().add(vector(starship.getDirection().getX() * 30, starship.getDirection().getY() * 30)), starship.getDirection(), true, tag));
            }
        }
        return newStarship;
    }
}
