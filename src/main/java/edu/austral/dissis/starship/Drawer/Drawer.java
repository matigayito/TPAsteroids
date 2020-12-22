package edu.austral.dissis.starship.Drawer;

import edu.austral.dissis.starship.Player.Player;
import edu.austral.dissis.starship.base.objects.Asteroid;
import edu.austral.dissis.starship.base.objects.Bullet;
import processing.core.PGraphics;

import java.util.List;

public class Drawer {

    private StarshipDrawer starshipDrawer;
    private AsteroidDrawer asteroidDrawer;
    private BulletDrawer bulletDrawer;

    public Drawer(AsteroidDrawer asteroidDrawer, BulletDrawer bulletDrawer, StarshipDrawer starshipDrawer){
        this.asteroidDrawer = asteroidDrawer;
        this.bulletDrawer = bulletDrawer;
        this.starshipDrawer = starshipDrawer;
    }

    public void draw(List<Player> players, List<List<Bullet>> bullets, List<Asteroid> asteroids, PGraphics graphics) {
        for (int i = 0; i < players.size(); i++) {
            drawStarship(players.get(i), graphics);
        }
        for (int i = 0; i < bullets.size(); i++) {
            drawBullets(bullets.get(i), graphics);
        }
        drawAsteroids(asteroids, graphics);
    }

    private void drawAsteroids(List<Asteroid> asteroids, PGraphics graphics){
        for (int i = 0; i < asteroids.size(); i++) {
            asteroidDrawer.draw(graphics, asteroids.get(i));
        }
    }

    private void drawBullets(List<Bullet> bullets, PGraphics graphics) {
        for (int i = 0; i < bullets.size(); i++) {
            bulletDrawer.draw(graphics, bullets.get(i));
        }
    }

    private void drawStarship(Player player, PGraphics graphics) {
        if (player.isGameOver()){
            starshipDrawer.draw(graphics, player.getStarship());
        }
    }

    public void drawGameOver(PGraphics graphics, List<Player> players) {
        graphics.pushMatrix();
        graphics.textSize(32);

        for (int i = 0; i < players.size(); i++) {
            String p = "Player " + (i+1) + " scored " + players.get(i).getScore() + " points ";
            graphics.text(p, 10, 10 + 40*(i+1));
        }
        //if(player1.getScore() > player2.getScore()){
        //    p1 += "and is the winner!";
        //}
        graphics.popMatrix();
    }
}
