package edu.austral.dissis.starship;

import edu.austral.dissis.starship.Collisionables.SquareCollisionable;
import edu.austral.dissis.starship.Drawer.AsteroidDrawer;
import edu.austral.dissis.starship.Drawer.BulletDrawer;
import edu.austral.dissis.starship.Drawer.StarshipDrawer;
import edu.austral.dissis.starship.Observer.LivesObserver;
import edu.austral.dissis.starship.Observer.PointsObservable;
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
import java.util.Random;
import java.util.Set;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;

public class CustomGameFramework implements GameFramework {

    private float Timer = 0;
    private float asteroidCounter = 1;

    private StarshipDrawer starshipDrawer;
    private AsteroidDrawer asteroidDrawer;
    private BulletDrawer bulletDrawer;

    private Player player1 = new Player(new Starship(vector(200, 200), vector(1, 0), true,"player-1"), new Controller(0x26,0x28,0x25,0x27, 0x20, "player-1") ,0, 3, "player-1"); //flechas de direccion y espacio
    private List<Bullet> bullets1 = new ArrayList<>();
    private Player player2 = new Player(new Starship(vector(800, 600), vector(-1, 0),true, "player-2"), new Controller(0x57,0x53,0x41,0x44,0x31, "player-2"),  0, 3, "player-2"); // wasd y 1
    private List<Bullet> bullets2 = new ArrayList<>();
    private List<Asteroid> asteroids = new ArrayList<>();
    private List<PointsObserver> points = new ArrayList<>();
    private List<LivesObserver> lives = new ArrayList<>();


    private final CollisionEngine engine = new CollisionEngine();

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings
            .setSize(1000, 800);

        asteroidDrawer = new AsteroidDrawer();
        points.add(player1);
        points.add(player2);
        bulletDrawer = new BulletDrawer(points);
        lives.add(player1);
        lives.add(player2);
        starshipDrawer = new StarshipDrawer(imageLoader.load("spaceship.png"), lives);

    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        if(player1.isGameOver() || player2.isGameOver()){
        updateStarship(keySet, timeSinceLastDraw);
        updateCannons();
        drawStarships(graphics);
        drawAsteroids(graphics);
        drawBullets(graphics);
        checkLimitsShip();
        checkLimitsAsteroids();
        checkLimitsBullets();
        checkCollisions();
        updateAsteroids(timeSinceLastDraw);
        }else{
            drawGameOver(graphics);
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

    private void checkLimitsShip(){
        Starship s1 = player1.getStarship();
        if (s1.getPosition().getX() < 0){
            player1.setStarship(new Starship(vector(1000, s1.getPosition().getY()), s1.getDirection(), true, player1.getTag()));
        }
        if (s1.getPosition().getX() > 1000){
            player1.setStarship(new Starship(vector(0, s1.getPosition().getY()), s1.getDirection(), true, player1.getTag()));
        }
        if (s1.getPosition().getY() < 0){
            player1.setStarship(new Starship(vector(s1.getPosition().getX(), 800), s1.getDirection(), true, player1.getTag()));
        }
        if (s1.getPosition().getY() > 800){
            player1.setStarship(new Starship(vector(s1.getPosition().getX(), 0), s1.getDirection(), true, player1.getTag()));
        }
        Starship s2 = player2.getStarship();
        if (s2.getPosition().getX() < 0){
            player2.setStarship(new Starship(vector(1000, s2.getPosition().getY()), s2.getDirection(), true, player2.getTag()));
        }
        if (s1.getPosition().getX() > 1000){
            player2.setStarship(new Starship(vector(0, s2.getPosition().getY()), s2.getDirection(), true, player2.getTag()));
        }
        if (s1.getPosition().getY() < 0){
            player2.setStarship(new Starship(vector(s2.getPosition().getX(), 800), s2.getDirection(), true, player2.getTag()));
        }
        if (s1.getPosition().getY() > 800){
            player2.setStarship(new Starship(vector(s2.getPosition().getX(), 0), s2.getDirection(), true, player2.getTag()));
        }
    }

    private void checkLimitsAsteroids(){
        for (int i = 0; i < asteroids.size(); i++) {
            if (asteroids.get(i).getPosition().getX() > 1000){
                asteroids.remove(i);
            }
        }
    }

    private void checkLimitsBullets(){
        for (int i = 0; i < bullets1.size()-1; i++) {
            if (!bullets1.isEmpty()) {
                if (bullets1.get(i).getPosition().getX() < 0) {
                    bullets1.remove(i);
                }
                if (bullets1.get(i).getPosition().getX() > 1000) {
                    bullets1.remove(i);
                }
                if (bullets1.get(i).getPosition().getY() < 0) {
                    bullets1.remove(i);
                }
                if (bullets1.get(i).getPosition().getY() > 1000) {
                    bullets1.remove(i);
                }
            }
        }
        for (int i = 0; i < bullets2.size()-1; i++) {
            if (!bullets2.isEmpty()) {
                if (bullets2.get(i).getPosition().getX() < 0) {
                    bullets2.remove(i);
                }
                if (bullets2.get(i).getPosition().getX() > 1000) {
                    bullets2.remove(i);
                }
                if (bullets2.get(i).getPosition().getY() < 0) {
                    bullets2.remove(i);
                }
                if (bullets2.get(i).getPosition().getY() > 1000) {
                    bullets2.remove(i);
                }
            }
        }
    }

    private void drawStarships(PGraphics graphics) {
        if (player1.isGameOver()){
        starshipDrawer.draw(graphics, player1.getStarship());
        }
        if (player2.isGameOver()){
        starshipDrawer.draw(graphics, player2.getStarship());
        }
    }

    private void drawAsteroids(PGraphics graphics){
        for (int i = 0; i < asteroids.size(); i++) {
            asteroidDrawer.draw(graphics, asteroids.get(i));
        }
    }

    private void drawBullets(PGraphics graphics){
        for (int i = 0; i < bullets1.size(); i++) {
            bulletDrawer.draw(graphics, bullets1.get(i).moveForward(2));
        }
        for (int i = 0; i < bullets2.size(); i++) {
            bulletDrawer.draw(graphics, bullets2.get(i).moveForward(2));
        }
    }

    private void updateStarship(Set<Integer> keySet, float timeSinceLastDraw) {
        Timer = Timer + timeSinceLastDraw / 100;
        if (player1.isGameOver()) {
            if (player1.getStarship().isAlive()){
                player1.controlShip(keySet, bullets1, timeSinceLastDraw);
            } else {
                player1.setStarship(new Starship(vector(200, 200), vector(1, 0), true, player1.getTag()));
            }
        }
        if (player2.isGameOver()){
            if (player2.getStarship().isAlive()){
                player2.controlShip(keySet, bullets2, timeSinceLastDraw);
            }else {
                player2.setStarship(new Starship(vector(800, 600), vector(-1, 0), true, player2.getTag()));
            }
        }
    }

    private void updateCannons(){
        for (int i = 0; i < bullets1.size(); i++) {
            if (bullets1.get(i).isAlive()) {
                bullets1.set(i, bullets1.get(i).moveForward(10));
            }else{
                bullets1.remove(i);
            }
        }
        for (int i = 0; i < bullets2.size(); i++) {
            if (bullets2.get(i).isAlive()) {
                bullets2.set(i, bullets2.get(i).moveForward(10));
            }else{
                bullets2.remove(i);
            }
        }
    }

    private void updateAsteroids(float timeSinceLastDraw){
        Timer = Timer + timeSinceLastDraw/100;
        if (asteroids.size() < 20 && Timer > 50) {
            asteroidCounter += 0.1f;
            Random random = new Random();
            asteroids.add(new Asteroid(vector(0, (float) random.nextInt(1000)), vector(1, 0), true));
            Timer = 0;
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

    public void drawGameOver(PGraphics graphics) {
        graphics.pushMatrix();
        String p1 = "Player 1 scored " + player1.getScore() + " points ";
        if(player1.getScore() > player2.getScore()){
            p1 += "and is the winner!";
        }
        String p2 = "Player 2 scored " + player2.getScore() + " points ";
        if(player2.getScore() > player1.getScore()){
            p2 += "and is the winner!";
        }
        graphics.textSize(32);
        graphics.text(p1,10,80);
        graphics.text(p2, 10, 80 + 40);
        graphics.popMatrix();
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}
