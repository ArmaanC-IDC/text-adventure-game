package mobs;

import player.Player;
import attack.ShieldBash;
import attack.DefensiveStance;
import attack.CorruptedStrike;
import attack.DesperateSwing;
import attack.Attack;

import java.util.*;

public class CorruptedKnight extends Mob {
    private String lastAttackName = "";
    private int repeatCount = 0;

    public CorruptedKnight() {
        super("Corrupted Knight", 150, 40, 2, new ArrayList<>());
        attacks.add(new ShieldBash());
        attacks.add(new DefensiveStance());
        attacks.add(new CorruptedStrike());
        attacks.add(new DesperateSwing());
    }

    public void performAttack(Player player) {
        //Deals with 2 phase mechanic
        List<Attack> usable = new ArrayList<>();
        for (Attack a : attacks) {
            if (hp > 75 && (a instanceof ShieldBash || a instanceof DefensiveStance)) {
                usable.add(a);
            } else if (hp <= 75 && (a instanceof CorruptedStrike || a instanceof DesperateSwing)) {
                usable.add(a);
            }
        }

        Attack chosen = usable.get(new java.util.Random().nextInt(usable.size()));

        // Handle repeated attack logic
        if (chosen.getName().equals(lastAttackName)) {
            repeatCount++;
            if (repeatCount >= 2) {
                System.out.println(name + "'s repeated attack is less effective!");
                // Apply penalty to damage or effect if needed
            }
        } else {
            repeatCount = 1;
            lastAttackName = chosen.getName();
        }

        System.out.println(name + " uses " + chosen.getName() + "!");
        chosen.execute(this, player);
    }
}