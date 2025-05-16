package attack;

import mobs.Mob;
import player.Player;

public class GroundSmash implements Attack {
    public String getName() {
        return "Ground Smash";
    }

    public void execute(Mob attacker, Player player) {
        int damage = 10 + new java.util.Random().nextInt(6); // 10-15
        if (attacker.getHp() <= 30) damage += 5;
        player.takeDamage(damage);
        if (Math.random() < 0.5) player.stun();
        System.out.println(attacker.getName() + " smashes the ground for " + damage + " damage!");
    }
}