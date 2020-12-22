package edu.austral.dissis.starship.Drawer;

import edu.austral.dissis.starship.Player.Player;
import edu.austral.dissis.starship.base.objects.Asteroid;
import edu.austral.dissis.starship.base.objects.Bullet;
import edu.austral.dissis.starship.base.objects.Starship;

import java.util.List;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;

public class LimitsChecker {
    public void check(List<Player> players, List<List<Bullet>> bullets, List<Asteroid> asteroids) {
        for (int i = 0; i < players.size(); i++) {
            checkStarship(players.get(i));
        }
        for (int i = 0; i < bullets.size(); i++) {
            checkBullets(bullets.get(i));
        }
        checkAsteroids(asteroids);
    }

    private void checkAsteroids(List<Asteroid> asteroids) {
        for (int i = 0; i < asteroids.size(); i++) {
            if (asteroids.get(i).getPosition().getX() > 1000){
                asteroids.remove(i);
            }
        }
    }

    private void checkBullets(List<Bullet> bullets) {
        for (int i = 0; i < bullets.size()-1; i++) {
            if (!bullets.isEmpty()) {
                if (bullets.get(i).getPosition().getX() < 0) {
                    bullets.remove(i);
                }
                if (bullets.get(i).getPosition().getX() > 1000) {
                    bullets.remove(i);
                }
                if (bullets.get(i).getPosition().getY() < 0) {
                    bullets.remove(i);
                }
                if (bullets.get(i).getPosition().getY() > 1000) {
                    bullets.remove(i);
                }
            }
        }
    }

    private void checkStarship(Player player) {
        Starship s1 = player.getStarship();
        if (s1.getPosition().getX() < 0){
            player.setStarship(new Starship(vector(1000, s1.getPosition().getY()), s1.getDirection(), true, player.getTag(), player.getStarship().getPosx(), player.getStarship().getPosy()));
        }
        if (s1.getPosition().getX() > 1000){
            player.setStarship(new Starship(vector(0, s1.getPosition().getY()), s1.getDirection(), true, player.getTag(), player.getStarship().getPosx(), player.getStarship().getPosy()));
        }
        if (s1.getPosition().getY() < 0){
            player.setStarship(new Starship(vector(s1.getPosition().getX(), 800), s1.getDirection(), true, player.getTag(), player.getStarship().getPosx(), player.getStarship().getPosy()));
        }
        if (s1.getPosition().getY() > 800){
            player.setStarship(new Starship(vector(s1.getPosition().getX(), 0), s1.getDirection(), true, player.getTag(), player.getStarship().getPosx(), player.getStarship().getPosy()));
        }
    }
}
