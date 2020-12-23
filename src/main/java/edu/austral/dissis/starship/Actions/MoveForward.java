package edu.austral.dissis.starship.Actions;

import edu.austral.dissis.starship.base.objects.Starship;

public class MoveForward implements ShipAction {

    @Override
    public Starship doAction(Starship starship) {
        starship = starship.moveForward(5);
        return starship;
    }
}
