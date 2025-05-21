package attack;

import mobs.Mob;
import player.Player;

public class Roar implements Attack {
    public String getName() {
        return "Roar";
    }

    public void execute(Mob attacker, Player player) {
        player.weaken();
        System.out.println(attacker.getName() + " roars fiercely, weakening the player!");
    }
}