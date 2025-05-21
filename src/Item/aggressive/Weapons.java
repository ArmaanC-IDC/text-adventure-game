package item.aggressive;

import item.Item;

public class Weapons extends Item{
    protected String name;
    protected int baseDamage;
    protected int maxDamage;
    protected boolean goldenApple;
    protected int damageReceived;

    public Weapons(String name, int baseDamage, int maxDamage) {
        super(name);
        this.baseDamage = baseDamage;
        this.maxDamage = maxDamage;
        goldenApple = false;
        damageReceived = 0;
    }


    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getMaxDamage(){
        return maxDamage;        
    }

    // will return weapon when asked for type in player class
    public String getType() {
        return "weapon";
    }
}
