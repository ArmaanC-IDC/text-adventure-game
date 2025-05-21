package attack;

import mobs.Mob;
import player.Player;

public class BoneStrike implements Attack {
    public String getName() {
        return "Bone Strike";
    }

    public void execute(Mob attacker, Player player) {
        int damage = 6 + new java.util.Random().nextInt(3); // 6-8
        player.takeDamage(damage);
        System.out.println(attacker.getName() + " strikes with bone for " + damage + " damage!");
    }
}