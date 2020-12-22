package edu.austral.dissis.starship;

import edu.austral.dissis.starship.Player.Player;
import edu.austral.dissis.starship.base.objects.Asteroid;
import edu.austral.dissis.starship.base.objects.Bullet;
import edu.austral.dissis.starship.base.objects.Starship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;

public class Updater {
    private float asteroidCounter = 1;
    private float timer;

    public void update(List<Player> players, List<List<Bullet>> bulletList, List<Asteroid> asteroids, float timeSinceLastDraw, Set<Integer> keySet) {
        for (int i = 0; i < players.size(); i++) {
            updateStarship(players.get(i), bulletList.get(i),keySet, timeSinceLastDraw);
        }
        for (int i = 0; i < bulletList.size(); i++) {
            updateBullets(bulletList.get(i));
        }
        updateAsteroids(asteroids, timeSinceLastDraw);
    }

    private void updateBullets(List<Bullet> bullets) {
        List<Bullet> toRemove = new ArrayList();
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).isAlive()) {
                bullets.set(i, bullets.get(i).moveForward(10));
            }else{
                toRemove.add(bullets.get(i));
            }
        }
        for (int i = 0; i < toRemove.size(); i++) {
            bullets.remove(toRemove.get(i));
        }
        toRemove.clear();
    }

    private void updateAsteroids(List<Asteroid> asteroids, float timeSinceLastDraw) {
        timer = timer + timeSinceLastDraw/100;
        if (asteroids.size() < 20 && timer > 50) {
            asteroidCounter += 0.1f;
            Random random = new Random();
            asteroids.add(new Asteroid(vector(0, (float) random.nextInt(1000)), vector(1, 0), true));
            timer = 0;
        }
        for (int i = 0; i < asteroids.size(); i++) {
            if (!asteroids.get(i).isAlive()){
                Random random = new Random();
                asteroids.set(i, new Asteroid(vector(0, (float) random.nextInt(1000)), vector(1, 0), true));
            }else{
                asteroids.set(i, asteroids.get(i).moveForward(asteroidCounter));
            }
        }
    }

    private void updateStarship(Player player, List<Bullet> bullets, Set<Integer> keySet, float timeSinceLastDraw) {
        timer = timer + timeSinceLastDraw / 100;
        if (player.isGameOver()) {
            if (player.getStarship().isAlive()){
                player.controlShip(keySet, bullets, timeSinceLastDraw);
            } else {
                player.setStarship(new Starship(vector(player.getStarship().getPosx(), player.getStarship().getPosy()), vector(0, -1), true, player.getTag(), player.getStarship().getPosx(), player.getStarship().getPosy()));
            }
        }
    }
}
