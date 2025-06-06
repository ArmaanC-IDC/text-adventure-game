package mobs;

import attack.QuickStab;
import attack.DirtyTrick;
import mobs.MobsLoader;

import java.util.ArrayList;

//Adds usable attacks, calls super constructor
public class GoblinScavenger extends Mob {
    public GoblinScavenger() {
        // super("Goblin Scavenger", 15, 10, 5, new ArrayList<>());
        super("Goblin Scavenger", 
            MobsLoader.getMobConfig("goblin", "hp"),
            MobsLoader.getMobConfig("goblin", "armor"),
            5, new ArrayList<>()
        );
        attacks.add(new QuickStab());
        attacks.add(new DirtyTrick());
    }
}
