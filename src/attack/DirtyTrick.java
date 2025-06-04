package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class DirtyTrick implements Attack {
    public String getName() {
        return "Dirty Trick";
    }

    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        int damage = 2 + new java.util.Random().nextInt(3); // 2-4
        if (player.getHp() < player.getMaxHp()) damage += 2; // Bonus dmg to wounded
        player.takeDamage(damage);
        if (Math.random() < 0.2) {
            player.stun();
            System.out.println("Player is stunned!");
        }
        Game.printText(attacker.getName() + " uses a dirty trick for " + damage + " damage!");
    }
}
