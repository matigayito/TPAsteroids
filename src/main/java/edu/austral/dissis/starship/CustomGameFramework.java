package edu.austral.dissis.starship;

import edu.austral.dissis.starship.Collisionables.SquareCollisionable;
import edu.austral.dissis.starship.Drawer.*;
import edu.austral.dissis.starship.Observer.LivesObserver;
import edu.austral.dissis.starship.Observer.PointsObserver;
import edu.austral.dissis.starship.Player.Controller;
import edu.austral.dissis.starship.Player.Player;
import edu.austral.dissis.starship.base.collision.CollisionEngine;
import edu.austral.dissis.starship.base.framework.GameFramework;
import edu.austral.dissis.starship.base.framework.ImageLoader;
import edu.austral.dissis.starship.base.framework.WindowSettings;
import edu.austral.dissis.starship.base.objects.Asteroid;
import edu.austral.dissis.starship.base.objects.Bullet;
import edu.austral.dissis.starship.base.objects.Starship;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;

public class CustomGameFramework implements GameFramework {

    private StarshipDrawer starshipDrawer;
    private AsteroidDrawer asteroidDrawer;
    private BulletDrawer bulletDrawer;
    private Drawer drawer;
    private LimitsChecker limitsChecker = new LimitsChecker();
    private Updater updater = new Updater();
    private CollisionEngine engine;

    private Player player1 = new Player(new Starship(vector(200, 200), vector(1, 0), true,"player-1", 200, 200), new Controller(0x26,0x28,0x25,0x27, 0x20, "player-1") ,0, 3, "player-1"); //flechas de direccion y espacio
    private List<Bullet> bullets1 = new ArrayList<>();
    private Player player2 = new Player(new Starship(vector(800, 600), vector(-1, 0),true, "player-2", 800, 600), new Controller(0x57,0x53,0x41,0x44,0x31, "player-2"),  0, 3, "player-2"); // wasd y 1
    private List<Bullet> bullets2 = new ArrayList<>();
    private List<Asteroid> asteroids = new ArrayList<>();
    private List<PointsObserver> points = new ArrayList<>();
    private List<LivesObserver> lives = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private List<List<Bullet>> bulletList = new ArrayList<>();

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings
            .setSize(1000, 800);

        players.add(player1);
        players.add(player2);
        bulletList.add(bullets1);
        bulletList.add(bullets2);

        asteroidDrawer = new AsteroidDrawer();
        points.add(player1);
        points.add(player2);
        bulletDrawer = new BulletDrawer(points);
        lives.add(player1);
        lives.add(player2);
        starshipDrawer = new StarshipDrawer(imageLoader.load("spaceship.png"), lives);
        drawer = new Drawer(asteroidDrawer, bulletDrawer, starshipDrawer);
        engine = new CollisionEngine();
    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        if(player1.isGameOver() || player2.isGameOver()){
        drawer.draw(players, bulletList, asteroids, graphics);
        limitsChecker.check(players, bulletList, asteroids);
        updater.update(players, bulletList, asteroids, timeSinceLastDraw, keySet);
        checkCollisions();
        }else{
            drawer.drawGameOver(graphics, players);
        }
    }

    private void checkCollisions() {
        List<SquareCollisionable> collisionables = new ArrayList<>();
        if (player1.isGameOver()){
        collisionables.add(starshipDrawer.getCollisionable(player1.getStarship()));
        }
        if (player2.isGameOver()){
        collisionables.add(starshipDrawer.getCollisionable(player2.getStarship()));
        }
        for (int i = 0; i < bullets1.size() ; i++) {
            collisionables.add(bulletDrawer.getCollisionable(bullets1.get(i)));
        }
        for (int i = 0; i < bullets2.size() ; i++) {
            collisionables.add(bulletDrawer.getCollisionable(bullets2.get(i)));
        }
        for (int i = 0; i < asteroids.size(); i++) {
            collisionables.add(asteroidDrawer.getCollisionable(asteroids.get(i)));
        }

        engine.checkCollisions(collisionables);
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}
