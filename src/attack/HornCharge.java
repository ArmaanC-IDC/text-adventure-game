package attack;

import mobs.Mob;
import player.Player;

public class HornCharge implements Attack {
    public String getName() {
        return "Horn Charge";
    }

    public void execute(Mob attacker, Player player) {
        int base = 15 + new java.util.Random().nextInt(6); // 15-20
        if (attacker.getHp() <= 30) base += 5;
        player.takeDamage(base);
        System.out.println(attacker.getName() + " charges with horns for " + base + " damage!");
    }
}