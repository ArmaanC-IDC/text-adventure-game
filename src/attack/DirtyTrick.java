package attack;

import mobs.Mob;
import player.Player;

public class DirtyTrick implements Attack {
    public String getName() {
        return "Dirty Trick";
    }

    public void execute(Mob attacker, Player player) {
        int damage = 2 + new java.util.Random().nextInt(3); // 2-4
        if (player.getHp() < player.getMaxHp()) damage += 2; // Bonus to wounded
        player.takeDamage(damage);
        if (Math.random() < 0.2) {
            player.stun();
            System.out.println("Player is stunned!");
        }
        System.out.println(attacker.getName() + " uses a dirty trick for " + damage + " damage!");
    }
}
