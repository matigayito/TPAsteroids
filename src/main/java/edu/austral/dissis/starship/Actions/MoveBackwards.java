package edu.austral.dissis.starship.Actions;

import edu.austral.dissis.starship.base.objects.Starship;

public class MoveBackwards implements ShipAction {

    @Override
    public Starship doAction(Starship starship) {
        starship = starship.moveBackwards(2);
        return starship;
    }
}
