package edu.austral.dissis.starship.Player;

import com.sun.tools.jdi.ObsoleteMethodImpl;
import edu.austral.dissis.starship.Observer.LivesObserver;
import edu.austral.dissis.starship.Observer.PointsObserver;
import edu.austral.dissis.starship.base.objects.Bullet;
import edu.austral.dissis.starship.base.objects.Starship;

import java.util.List;
import java.util.Observable;
import java.util.Set;

public class Player implements PointsObserver, LivesObserver {
    Starship starship;
    Controller controller;
    private int score;
    private boolean gameOver;
    private int lives;
    private String tag;

    public Player(Starship s, Controller c, int score, int lives, String name){
        starship = s;
        controller = c;
        this.score = score;
        this.lives = lives;
        this.gameOver = lives > 0;
        tag = name;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public Starship getStarship(){
        return starship;
    }

    public int getScore(){
        return score;
    }

    public void setStarship(Starship starship){
        this.starship = starship;
    }

    @Override
    public void addPoints(int points) {
        score += points;
        System.out.println(score);
    }

    @Override
    public void looseLife() {
        if (starship.isAlive()){
        lives--;
        }
        gameOver = lives > 0;
    }

    @Override
    public String getTag() {
        return tag;
    }

    public void controlShip(Set<Integer> keySet, List<Bullet> bullets1, float timeSinceLastDraw) {
        starship = controller.keyHandle(keySet, starship, bullets1, timeSinceLastDraw);
    }
}
