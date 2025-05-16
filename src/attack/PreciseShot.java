package attack;

import mobs.Mob;
import player.Player;

public class PreciseShot implements Attack {
    public String getName() {
        return "Precise Shot";
    }

    public void execute(Mob attacker, Player player) {
        int damage = 10 + new java.util.Random().nextInt(3); // 10-12
        player.takeDamage(damage);
        System.out.println(attacker.getName() + " lands a precise shot for " + damage + " damage!");
    }
}