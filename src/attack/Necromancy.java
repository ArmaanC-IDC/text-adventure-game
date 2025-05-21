package attack;

import mobs.Mob;
import player.Player;

public class Necromancy implements Attack {
    public String getName() {
        return "Necromancy";
    }

    public void execute(Mob attacker, Player player) {
        System.out.println(attacker.getName() + " summons a skeleton ally!");
        // ADD MOB SUMMON LOGIC
    }
}
