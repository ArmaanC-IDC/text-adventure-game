package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class GroundSmash implements Attack {
    public String getName() {
        return "Ground Smash";
    }

    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        int damage = 10 + new java.util.Random().nextInt(6); // 10-15
        if (attacker.getHp() <= 30) damage += 5; //Extra dmg to wounded
        player.takeDamage(damage);
        if (Math.random() < 0.5) player.stun();
            Game.printText(attacker.getName() + " smashes the ground for " + damage + " damage!");
    }
}