package mobs;

import attack.AcidSplash;
import attack.PoisonTouch;
import rooms.MobRoom;
import rooms.Room;
<<<<<<< HEAD
import mobs.Mob;
import player.Player;
import general.Game;
=======
>>>>>>> 567fd9f24c08e6a0552cf5758bc8efee57c0f486

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
<<<<<<< HEAD
            Room currentRoom = Game.getGame().getCurrentRoom();
            if (currentRoom instanceof MobRoom) {
                ((MobRoom) currentRoom).getMobs().add(new CrumblingSkeleton());
                Game.printText(name + " splits into two smaller slimes!");
=======
            Game.printText(name + " splits into two smaller slimes!");
            Room currentRoom = Game.getGame().getCurrentRoom();
            if (currentRoom instanceof MobRoom) {
                ((MobRoom) currentRoom).getMobs().add(new SplitSlime());
>>>>>>> 567fd9f24c08e6a0552cf5758bc8efee57c0f486
            }
        }
    }
}