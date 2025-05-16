package attack;

import mobs.Mob;
import player.Player;

public class BindingArrow implements Attack {
    public String getName() {
        return "Binding Arrow";
    }

    public void execute(Mob attacker, Player player) {
        int damage = 6 + new java.util.Random().nextInt(3); // 6-8
        player.takeDamage(damage);
        player.stun();
        System.out.println(attacker.getName() + " shoots a binding arrow for " + damage + " damage and stuns!");
    }
}