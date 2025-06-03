// Bow.java
package item.aggressive;

import java.util.ArrayList;

import general.Game;
import item.Item;
import player.Player;

public class Bow extends Weapon {
    public Bow() {
        super("Bow", 5, 10); //child class of weapon ->item
    }

    private int countArrows() { //used to count how many arrows the player has
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

    private boolean removeOneArrow() { //method to remove arrow from inventory
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
        int arrowCount = countArrows(); //num of arrows
        Player p = Game.getPlayer();

        if (arrowCount == 0) {//safety to use if player doesn't have arrows
            Game.printText("You have no arrows.");
            return false;
        }

        boolean removed = removeOneArrow(); //true false switch
        if (!removed) {
            Game.printText("Error: could not remove an arrow from your inventory."); // normally this shouldn't happen
            return false;
        }

        boolean attackSucceeded = super.useItem(args); //if use item works and also attacks the monsters
        if (!attackSucceeded) {
            p.getInventory().add(new Arrow()); //adds back the arrow if the attack didn't work
        }
        p.setSkipDamage(true); //skip the damage as arrow is a long ranged weapon
        return attackSucceeded;
    }

    
}
