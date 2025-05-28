package mobs;

import attack.ShieldBash;
import player.Player;
import attack.DefensiveStance;
import attack.CorruptedStrike;
import attack.DesperateSwing;
import attack.Attack;
import general.Game;
import player.Player;
import item.passive.Horn;

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
                Game.printText(name + "'s repeated attack is less effective!");
                // Apply penalty to damage or effect if needed
            }
        } else {
            repeatCount = 1;
            lastAttackName = chosen.getName();
        }

        System.out.println(name + " uses " + chosen.getName() + "!");
        chosen.execute(this, player);
    }

    protected void onDeath() {
        // Call parent onDeath for normal drop logic AND to increment counters
        super.onDeath();
        
        // End game logic for final boss
        Game.printText("==================================================");
        Game.printText("VICTORY! The Corrupted Knight crumbles to dust!");
        Game.printText("The ancient curse binding this dungeon is broken...");
        Game.printText("Sunlight streams through cracks in the ceiling above.");
        Game.printText("The dungeon's darkness retreats as you claim victory!");
        Game.printText("");
        Game.printText("You have conquered the depths and emerged victorious!");
        Game.printText("The treasures of the dungeon are yours to claim.");
        Game.printText("");
        Game.printText("FINAL STATISTICS:");
        Game.printText("Monsters defeated: " + Game.getMobsKilled());
        Game.printText("Total coins collected: " + Game.getTotalCoinsCollected());
        Game.printText("==================================================");
        Game.printText("Thank you for playing, brave dungeon crawler!");
        
        // End the game
        Game.getGame().setRunning(false);
    }
}