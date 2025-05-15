package item.aggressive;

public class Weapons{
    protected String name;
    protected int baseDamage;
    protected int maxDamage;
    protected boolean goldenApple;
    protected int damageReceived;

    public Weapons(String name, int baseDamage, int maxDamage) {
        this.name = name;
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
}
