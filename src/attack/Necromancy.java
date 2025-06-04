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

    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        Room currentRoom = Game.getGame().getCurrentRoom();
        //Summon an ally
        if (currentRoom instanceof MobRoom) {
            ((MobRoom) currentRoom).getMobs().add(new CrumblingSkeleton());
            Game.printText(attacker.getName() + " summons a skeleton ally!");
        }
    }
}
