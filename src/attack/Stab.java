package attack;

import mobs.Mob;
import player.Player;

public class Stab implements Attack {
    public String getName() {
        return "Stab";
    }

    public void execute(Mob attacker, Player player) {
        int damage = 4 + new java.util.Random().nextInt(3); // 4–6
        player.takeDamage(damage);
        System.out.println(attacker.getName() + " stabs quickly for " + damage + " damage!");
    }
}

