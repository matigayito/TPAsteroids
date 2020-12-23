package edu.austral.dissis.starship.Player;

import edu.austral.dissis.starship.Actions.ShipAction;
import edu.austral.dissis.starship.base.objects.Bullet;
import edu.austral.dissis.starship.base.objects.Starship;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static edu.austral.dissis.starship.base.vector.Vector2.vector;

public class Controller {
    private int shoot;
    private String tag;
    private Map<Integer, ShipAction> actionMap;

    public Controller(int shoot, String tag, Map<Integer, ShipAction> map) {
        this.shoot = shoot;
        this.tag = tag;
        actionMap = map;
    }

    public Starship keyHandle(Set<Integer> keySet, Starship starship, List<Bullet> bullets, float timeSinceLastDraw){
        Starship newStarship = starship;
        Iterator<Integer> iterator = keySet.iterator();
        for (int i = 0; i < keySet.size(); i++) {
            int key = iterator.next();
            if (actionMap.containsKey(key)){
                newStarship = actionMap.get(key).doAction(newStarship);
            }
        }

        if (keySet.contains(shoot)) {
            if (timeSinceLastDraw > 20 && bullets.size() < 10) {
                bullets.add(new Bullet(starship.getPosition().add(vector(starship.getDirection().getX() * 30, starship.getDirection().getY() * 30)), starship.getDirection(), true, tag));
            }
        }
        return newStarship;
    }
}
