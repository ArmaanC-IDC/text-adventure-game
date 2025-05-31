// Bow.java
package item.aggressive;

import java.util.ArrayList;

import general.Game;
import item.Item;
import player.Player;

public class Bow extends Weapon {
    public Bow() {
        super("Bow", 5, 10);
    }

    private int countArrows() {
        int count = 0;
        Player p = Game.getPlayer();
        ArrayList<Item> inventory = p.getInventory();
        for (Item item : inventory) {
            if (item instanceof Arrow) {
                count++;
            }
        }
        return count;
    }

    private boolean removeOneArrow() {
        Player p = Game.getPlayer();
        ArrayList<Item> inventory = p.getInventory();
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item instanceof Arrow) {
                inventory.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean useItem(String[] args) {
        int arrowCount = countArrows();
        Player p = Game.getPlayer();

        if (arrowCount == 0) {
            Game.printText("You have no arrows.");
            return false;
        }

        boolean removed = removeOneArrow();
        if (!removed) {
            Game.printText("Error: could not remove an arrow from your inventory."); // normally this shouldn't happen
            return false;
        }

        boolean attackSucceeded = super.useItem(args);
        if (!attackSucceeded) {
            p.getInventory().add(new Arrow());
        }
        return attackSucceeded;
    }
}
