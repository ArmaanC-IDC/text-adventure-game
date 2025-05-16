package attack;
import player.Player;

import mobs.Mob;

public interface Attack {
    String getName();
    void execute(Mob attacker, Player player);
}