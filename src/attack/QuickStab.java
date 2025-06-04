package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class QuickStab implements Attack {
    public String getName() {
        return "Quick Stab";
    }

    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        int baseDamage = 4 + new java.util.Random().nextInt(3); // 4-6
        if (player.getHp() < player.getMaxHp()) baseDamage += 2; // Bonus dmg to wounded
        player.takeDamage(baseDamage);
        Game.printText(attacker.getName() + " stabs for " + baseDamage + " damage!");
    }
}

