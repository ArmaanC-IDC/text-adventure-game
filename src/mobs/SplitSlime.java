package mobs;

import attack.AcidSplash;
import attack.PoisonTouch;
import rooms.MobRoom;
import rooms.Room;
import mobs.Mob;
import player.Player;
import general.Game;

import java.util.ArrayList;

public class SplitSlime extends Mob {

    public SplitSlime(String id) {
        super("Split Slime", 20, 5, 2, new ArrayList<>());
        attacks.add(new AcidSplash());
        attacks.add(new PoisonTouch());
    }

    public SplitSlime() {
        super("Split Slime", 20, 5, 2, new ArrayList<>());
        attacks.add(new AcidSplash());
        attacks.add(new PoisonTouch());
    }

    public void takeDamage(int rawDamage) {
        super.takeDamage(rawDamage);
        if (this.isAlive() && this.getHp() <= 5) {
            Room currentRoom = Game.getGame().getCurrentRoom();
            if (currentRoom instanceof MobRoom) {
                ((MobRoom) currentRoom).getMobs().add(new SplitSlime());
                Game.printText(name + " splits into two smaller slimes!");
            }
        }
    }
}