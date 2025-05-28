package mobs;

import attack.PreciseShot;
import attack.BindingArrow;
import attack.Necromancy;
import general.Game;
import item.passive.*;

import java.util.ArrayList;

public class HollowEyedRanger extends Mob {
    public HollowEyedRanger() {
        super("Hollow-Eyed Ranger", 90, 15, 4, new ArrayList<>());
        attacks.add(new PreciseShot());
        attacks.add(new BindingArrow());
        attacks.add(new Necromancy());
    }

    public void takeDamage(int rawDamage) {
        if (Math.random() < 0.25) {
            Game.printText(name + " dodges the attack!");
            return;
        }
        super.takeDamage(rawDamage);
    }

    protected void onDeath() {
        Game.printText("The ranger dropped it's pendant");
        Game.getGame().getCurrentRoom().getItems().add(new Pendant());
    }
}