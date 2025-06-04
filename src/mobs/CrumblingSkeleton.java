package mobs;

import attack.BoneStrike;
import attack.Reassemble;

import java.util.ArrayList;

//Adds usable attacks, calls super constructor
public class CrumblingSkeleton extends Mob {
    public CrumblingSkeleton() {
        super("Crumbling Skeleton", 
            MobsLoader.getMobConfig("skeleton", "hp"),
            MobsLoader.getMobConfig("skeleton", "armor"),
            3, new ArrayList<>()
        );
        attacks.add(new BoneStrike());
        attacks.add(new Reassemble());
    }
}