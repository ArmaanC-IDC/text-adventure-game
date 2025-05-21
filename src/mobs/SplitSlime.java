package mobs;

import attack.AcidSplash;
import attack.PoisonTouch;
import general.Game;
import rooms.MobRoom;

import java.util.ArrayList;

public class SplitSlime extends Mob {
    public SplitSlime() {
        super("Split Slime", 20, 5, 2, new ArrayList<>());
        attacks.add(new AcidSplash());
        attacks.add(new PoisonTouch());
    }

    public void takeDamage(int rawDamage) {
        super.takeDamage(rawDamage);
        if (this.isAlive() && this.getHp() <= 5) {
            Game.printText(name + " splits into two smaller slimes!");
            MobRoom.getMobs().add(new SplitSlime());
        }
    }
}