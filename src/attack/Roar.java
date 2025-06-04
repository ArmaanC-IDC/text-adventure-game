package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class Roar implements Attack {
    public String getName() {
        return "Roar";
    }

    //Execute on player, handle affects, display attack msg
    public void execute(Mob attacker, Player player) {
        player.weaken();
        Game.printText(attacker.getName() + " roars fiercely, weakening the player!");
    }
}