package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class BoneStrike implements Attack {
    public String getName() {
        return "Bone Strike";
    }

    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        int damage = 6 + new java.util.Random().nextInt(3); // 6-8
        player.takeDamage(damage);
        Game.printText(attacker.getName() + " strikes with bone for " + damage + " damage!");
    }
}