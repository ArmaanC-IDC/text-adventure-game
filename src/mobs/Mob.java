package mobs;
import java.util.ArrayList;
import java.util.Random;

import attack.Attack;
import player.Player;
import general.Game;
import item.Item;
import item.ItemPool;
import item.Passive.Coins;

public class Mob {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int armor;
    protected int speed;
    protected ArrayList<Attack> attacks;
    protected double dropChance;
    protected static Random random = new Random();

    public Mob(String name, int hp, int armor, int speed, ArrayList<Attack> attacks) {
        this.name = name;
        this.dropChance = 0.3;
        this.hp = this.maxHp = hp;
        this.armor = armor;
        this.speed = speed;
        this.attacks = attacks;
    }

    public void takeDamage(int rawDamage) {
        int damage = rawDamage * (100 - armor) / 100;
        hp -= damage;
        Game.printText(name + " takes " + damage + " damage!");
        
        if (hp <= 0) {
            hp = 0;
            onDeath();
        }
    }

    protected void onDeath() {
        Game.printText(name + " has been defeated!");

        if (Math.random() < dropChance) {
            Item droppedItem = ItemPool.getRandomItem();
            Game.getGame().getCurrentRoom().getItems().add(new Coins());
            if (droppedItem != null) {
                Game.getGame().getPlayer().getInventory().add(droppedItem);
                Game.printText("The " + name + " dropped: " + droppedItem.getName() + "!");
                Game.printText("1 Coins has been added your inventory");
            }
            } else {
                Game.printText("The " + name + " didn't drop anything.");
                Game.getGame().getCurrentRoom().getItems().add(new Coins());
                Game.printText("2 Coins have been added your inventory");
        }
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
        Game.printText(name + " heals " + amount + " HP!");
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void performAttack(Player player) {
        Attack attack = attacks.get((int)(Math.random() * attacks.size()));
        // Game.printText(name + " uses " + attack.getName() + "!");
        attack.execute(this, player);
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " with " + hp + " hp";
    }
}
