package mobs;

import attack.QuickStab;
import attack.DirtyTrick;

import java.util.ArrayList;

public class GoblinScavenger extends Mob {
    public GoblinScavenger() {
        super("Goblin Scavenger", 15, 10, 5, new ArrayList<>());
        attacks.add(new QuickStab());
        attacks.add(new DirtyTrick());
    }
}
