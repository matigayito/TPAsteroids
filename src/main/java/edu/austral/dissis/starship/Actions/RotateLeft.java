package edu.austral.dissis.starship.Actions;

import edu.austral.dissis.starship.base.objects.Starship;
import processing.core.PConstants;

public class RotateLeft implements ShipAction {
    @Override
    public Starship doAction(Starship starship) {
        starship = starship.rotate(-1 * PConstants.PI / 60);
        return starship;
    }
}
