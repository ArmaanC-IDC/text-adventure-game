package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class HornCharge implements Attack {
    public String getName() {
        return "Horn Charge";
    }

    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        int base = 15 + new java.util.Random().nextInt(6); // 15-20
        if (attacker.getHp() <= 30) base += 5; //Extra dmg to wounded
        player.takeDamage(base);
        Game.printText(attacker.getName() + " charges with horns for " + base + " damage!");
    }
}