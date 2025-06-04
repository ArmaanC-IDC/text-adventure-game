package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class ShieldBash implements Attack {
    public String getName() {
        return "Shield Bash";
    }
    
    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        int damage = 8 + new java.util.Random().nextInt(3); // 8-10
        player.takeDamage(damage);
        player.stun();
        Game.printText(attacker.getName() + " bashes the player for " + damage + " damage and stuns!");
    }
}