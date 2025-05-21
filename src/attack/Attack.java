package attack;
import player.player;

import mobs.Mob;

public interface Attack {
    String getName();
    void execute(Mob attacker, player player);
}