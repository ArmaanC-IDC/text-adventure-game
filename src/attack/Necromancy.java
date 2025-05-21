package attack;

import mobs.CrumblingSkeleton;
import mobs.Mob;
import player.Player;
import rooms.MobRoom;
import rooms.Room;
import general.Game;

public class Necromancy implements Attack {
    public String getName() {
        return "Necromancy";
    }

    public void execute(Mob attacker, Player player) {
        Game.printText(attacker.getName() + " summons a skeleton ally!");
        Room currentRoom = Game.getGame().getCurrentRoom();
        if (currentRoom instanceof MobRoom) {
            ((MobRoom) currentRoom).getMobs().add(new CrumblingSkeleton());
        }
    }
}
