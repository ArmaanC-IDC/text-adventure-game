package attack;

import mobs.Mob;
import player.player;
import general.Game;

public class ShieldBash implements Attack {
    public String getName() {
        return "Shield Bash";
    }
    
    public void execute(Mob attacker, player player) {
        int damage = 8 + new java.util.Random().nextInt(3); // 8-10
        player.takeDamage(damage);
        player.stun();
        Game.printText(attacker.getName() + " bashes the player for " + damage + " damage and stuns!");
    }
}