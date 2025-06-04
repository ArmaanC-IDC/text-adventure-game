package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class PoisonTouch implements Attack {
    public String getName() {
        return "Poison Touch";
    }

    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        int damage = 2;
        player.takeDamage(damage);
        player.poison();
        Game.printText(attacker.getName() + " touches and poisons for " + damage + " damage!");
    }
}
