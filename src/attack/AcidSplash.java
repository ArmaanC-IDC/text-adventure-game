package attack;

import general.Game;
import mobs.Mob;
import player.Player;

public class AcidSplash implements Attack {
    public String getName() {
        return "Acid Splash";
    }

    public void execute(Mob attacker, Player player) {
        int damage = 3 + new java.util.Random().nextInt(3); // 3-5
        player.takeDamage(damage);
        Game.printText(attacker.getName() + " splashes acid for " + damage + " damage!");
    }
}