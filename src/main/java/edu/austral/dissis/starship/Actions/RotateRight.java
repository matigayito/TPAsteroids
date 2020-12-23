package edu.austral.dissis.starship.Actions;

import edu.austral.dissis.starship.base.objects.Starship;
import processing.core.PConstants;

public class RotateRight implements ShipAction{
    @Override
    public Starship doAction(Starship starship) {
        starship = starship.rotate(PConstants.PI / 60);
        return starship;
    }
}
