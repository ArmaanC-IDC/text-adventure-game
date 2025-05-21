package attack;

import mobs.Mob;
import player.player;
import general.Game;

public class BoneStrike implements Attack {
    public String getName() {
        return "Bone Strike";
    }

    public void execute(Mob attacker, player player) {
        int damage = 6 + new java.util.Random().nextInt(3); // 6-8
        player.takeDamage(damage);
        Game.printText(attacker.getName() + " strikes with bone for " + damage + " damage!");
    }
}