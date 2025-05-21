package attack;

import mobs.Mob;
import player.player;
import general.Game;

public class Roar implements Attack {
    public String getName() {
        return "Roar";
    }

    public void execute(Mob attacker, player player) {
        player.weaken();
        Game.printText(attacker.getName() + " roars fiercely, weakening the player!");
    }
}