package mobs;

import attack.AcidSplash;
import attack.PoisonTouch;
import rooms.MobRoom;
import rooms.Room;
import general.Game;

import java.util.ArrayList;

//Adds usable attacks, calls super constructor
public class SplitSlime extends Mob {
    private boolean hasSplit = false;
    public SplitSlime() {
        // super("Split Slime", 20, 5, 2, new ArrayList<>());
        super("Split Slime", 
            MobsLoader.getMobConfig("slime", "hp"),
            MobsLoader.getMobConfig("slime", "armor"),
            2, new ArrayList<>()
        );
        attacks.add(new AcidSplash());
        attacks.add(new PoisonTouch());
    }

    public void takeDamage(int rawDamage) {
        super.takeDamage(rawDamage);
        if (this.isAlive() && this.getHp() <= 5 && !hasSplit) {
            Room currentRoom = Game.getGame().getCurrentRoom();
            if (currentRoom instanceof MobRoom) {
                ((MobRoom) currentRoom).getMobs().add(new SubSlime());
                Game.printText(name + " splits, creating an extra sub slime!");
                hasSplit = true;
            }
        }
    }
}