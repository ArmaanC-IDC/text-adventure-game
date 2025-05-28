package mobs;

import java.util.ArrayList;

import attack.AcidSplash;
import attack.PoisonTouch;

public class SubSlime extends Mob {
    public SubSlime() {
        super("Sub Slime", 10, 3, 1, new ArrayList<>());
        attacks.add(new AcidSplash());
        attacks.add(new PoisonTouch());
    }
    // Subslies don't override takeDamage, so they can't split
}
