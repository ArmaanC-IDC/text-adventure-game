package attack;

import mobs.CrumblingSkeleton;
import mobs.Mob;
import player.player;
import rooms.MobRoom;
import general.Game;

public class Necromancy implements Attack {
    public String getName() {
        return "Necromancy";
    }

    public void execute(Mob attacker, player player) {
        Game.printText(attacker.getName() + " summons a skeleton ally!");
        MobRoom.getMobs().add(new CrumblingSkeleton());
    }
}
