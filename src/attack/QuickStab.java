package attack;

import mobs.Mob;
import player.player;
import general.Game;

public class QuickStab implements Attack {
    public String getName() {
        return "Quick Stab";
    }

    public void execute(Mob attacker, player player) {
        int baseDamage = 4 + new java.util.Random().nextInt(3); // 4-6
        if (player.getHp() < player.getMaxHp()) baseDamage += 2; // Bonus to wounded
        player.takeDamage(baseDamage);
        Game.printText(attacker.getName() + " stabs for " + baseDamage + " damage!");
    }
}

