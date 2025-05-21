package Item.aggressive;

public class Sword extends Weapons {
    public Sword() {
        super("Sword", 10, 20);
    }

    public int damageDealt() {
        int damage = baseDamage + (int) (Math.random() * (maxDamage - baseDamage + 1));
        if (goldenApple) {
            damage += 5;
        }

        return damage;
    }
}
