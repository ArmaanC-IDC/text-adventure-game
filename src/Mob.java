import java.util.ArrayList;

public class Mob {
    private int hp;
    private int armor;
    private int speed;
    private ArrayList<Attack> attacks;

    public Mob(int hp, int armor, int speed, ArrayList<Attack> attacks) {
        this.hp = hp;
        this.armor = armor;
        this.speed = speed;
        this.attacks = attacks;
    }

    // Getters
    public int getHp() {
        return hp;
    }

    public int getArmor() {
        return armor;
    }

    public int getSpeed() {
        return speed;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    // Setters
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Methods
    public void takeDamage(int damage) {
        int actualDamage = damage * (100 - armor) / 100;
        this.hp -= actualDamage;
        System.out.println("Mob takes " + actualDamage + " damage!");
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void performAttack(Mob target) {
        if (attacks.isEmpty()) return;

        Attack attack = attacks.get((int) (Math.random() * attacks.size()));
        System.out.println("Mob uses " + attack.getName() + "!");
        attack.execute(this, target);
    }
}
