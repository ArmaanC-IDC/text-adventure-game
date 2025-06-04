package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class CorruptedStrike implements Attack {
    public String getName() {
        return "Corrupted Strike";
    }

    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        int damage = 12 + new java.util.Random().nextInt(4); // 12-15
        player.takeDamage(damage);
        player.poison();
        Game.printText(attacker.getName() + " uses a corrupted strike for " + damage + " damage and poisons!");
    }
}
