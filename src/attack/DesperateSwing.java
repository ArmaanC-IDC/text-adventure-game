package attack;

import mobs.Mob;
import player.Player;

public class DesperateSwing implements Attack {
    public String getName() {
        return "Desperate Swing";
    }

    public void execute(Mob attacker, Player player) {
        int damage = 20 + new java.util.Random().nextInt(6); // 20-25
        player.takeDamage(damage);
        attacker.setArmor(20);
        System.out.println(attacker.getName() + " uses a desperate swing for " + damage + " damage but lowers their armor to 20%!");
    }
}
