package attack;

import mobs.Mob;
import player.player;
import general.Game;

public class DirtyTrick implements Attack {
    public String getName() {
        return "Dirty Trick";
    }

    public void execute(Mob attacker, player player) {
        int damage = 2 + new java.util.Random().nextInt(3); // 2-4
        if (player.getHp() < player.getMaxHp()) damage += 2; // Bonus to wounded
        player.takeDamage(damage);
        if (Math.random() < 0.2) {
            player.stun();
            System.out.println("Player is stunned!");
        }
        Game.printText(attacker.getName() + " uses a dirty trick for " + damage + " damage!");
    }
}
