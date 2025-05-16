package mobs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import player.Player;
import attack.Attack;

public class Mob {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int armor;
    protected int speed;
    protected ArrayList<Attack> attacks;
    protected Set<String> statusEffects = new HashSet<>();

    public Mob(String name, int hp, int armor, int speed, ArrayList<Attack> attacks) {
        this.name = name;
        this.hp = this.maxHp = hp;
        this.armor = armor;
        this.speed = speed;
        this.attacks = attacks;
    }

    public void takeDamage(int rawDamage) {
        int damage = rawDamage * (100 - armor) / 100;
        hp -= damage;
        System.out.println(name + " takes " + damage + " damage!");
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
        System.out.println(name + " heals " + amount + " HP!");
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void performAttack(Player player) {
        if (statusEffects.contains("stunned")) {
            System.out.println(name + " is stunned and loses their turn!");
            statusEffects.remove("stunned");
            return;
        }
        Attack attack = attacks.get((int)(Math.random() * attacks.size()));
        System.out.println(name + " uses " + attack.getName() + "!");
        attack.execute(this, player);
    }

    public void processEffects() {
        if (statusEffects.contains("poisoned")) {
            System.out.println(name + " suffers poison damage!");
            takeDamage(3);
        }
    }

    public void addEffect(String effect) {
        statusEffects.add(effect);
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }
}
