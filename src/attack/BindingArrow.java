package attack;

import general.Game;
import mobs.Mob;
import player.Player;

public class BindingArrow implements Attack {
    public String getName() {
        return "Binding Arrow";
    }
    
    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        int damage = 6 + new java.util.Random().nextInt(3); // 6-8
        player.takeDamage(damage);
        player.stun();
        Game.printText(attacker.getName() + " shoots a binding arrow for " + damage + " damage and stuns!");
    }
}