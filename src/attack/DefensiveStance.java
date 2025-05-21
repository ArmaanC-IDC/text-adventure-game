package attack;

import mobs.Mob;
import player.Player;
import general.Game;

public class DefensiveStance implements Attack {
    public String getName() {
        return "Defensive Stance";
    }

    public void execute(Mob attacker, Player player) {
        attacker.setArmor(60);
        Game.printText(attacker.getName() + " takes a defensive stance! Armor raised to 60% for 2 turns.");
    }
}
