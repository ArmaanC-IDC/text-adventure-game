package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class Reassemble implements Attack {
    public String getName() {
        return "Reassemble";
    }

    public void execute(Mob attacker, Player player) {
        if (attacker.getHp() < attacker.getMaxHp() / 2) {
            attacker.heal(10);
            Game.printText(attacker.getName() + " reassembles and heals!");
        } else {
            Game.printText(attacker.getName() + " tried to reasseble but was too healthy...");
        }
    }
}
