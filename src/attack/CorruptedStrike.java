package attack;

import mobs.Mob;
import player.player;
import general.Game;

public class CorruptedStrike implements Attack {
    public String getName() {
        return "Corrupted Strike";
    }

    public void execute(Mob attacker, player player) {
        int damage = 12 + new java.util.Random().nextInt(4); // 12-15
        player.takeDamage(damage);
        player.poison();
        Game.printText(attacker.getName() + " uses a corrupted strike for " + damage + " damage and poisons!");
    }
}
