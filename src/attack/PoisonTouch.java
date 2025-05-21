package attack;

import mobs.Mob;
import player.Player;

public class PoisonTouch implements Attack {
    public String getName() {
        return "Poison Touch";
    }

    public void execute(Mob attacker, Player player) {
        int damage = 2;
        player.takeDamage(damage);
        player.poison();
        System.out.println(attacker.getName() + " touches and poisons for " + damage + " damage!");
    }
}
