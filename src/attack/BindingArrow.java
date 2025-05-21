package attack;

import general.Game;
import mobs.Mob;
import player.player;

public class BindingArrow implements Attack {
    public String getName() {
        return "Binding Arrow";
    }

    public void execute(Mob attacker, player player) {
        int damage = 6 + new java.util.Random().nextInt(3); // 6-8
        player.takeDamage(damage);
        player.stun();
        Game.printText(attacker.getName() + " shoots a binding arrow for " + damage + " damage and stuns!");
    }
}