package attack;

import mobs.Mob;
import player.Player;

public class DefensiveStance implements Attack {
    public String getName() {
        return "Defensive Stance";
    }

    public void execute(Mob attacker, Player player) {
        attacker.setArmor(60);
        System.out.println(attacker.getName() + " takes a defensive stance! Armor raised to 60% for 2 turns.");
    }
}
