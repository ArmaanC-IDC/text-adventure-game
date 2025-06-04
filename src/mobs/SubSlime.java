package mobs;

import java.util.ArrayList;

import attack.AcidSplash;
import attack.PoisonTouch;

//Spawned by slime when at low HP

//Adds usable attacks, calls super constructor
public class SubSlime extends Mob {
    public SubSlime() {
        // super("Sub Slime", 10, 3, 1, new ArrayList<>());
        super("Sub Slime", 
            MobsLoader.getMobConfig("subSlime", "hp"),
            MobsLoader.getMobConfig("subSlime", "armor"),
            1, new ArrayList<>()
        );
        attacks.add(new AcidSplash());
        attacks.add(new PoisonTouch());
    }
    // Subslies don't override takeDamage, so they can't split
}
