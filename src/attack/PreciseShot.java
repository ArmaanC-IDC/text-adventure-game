package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class PreciseShot implements Attack {
    public String getName() {
        return "Precise Shot";
    }

    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        int damage = 10 + new java.util.Random().nextInt(3); // 10-12
        player.takeDamage(damage);
        Game.printText(attacker.getName() + " lands a precise shot for " + damage + " damage!");
    }
}