public class Weapons{
    private String name;
    private int baseDamage;
    private boolean goldenApple;
    private int damageRecieved;

    public Weapons(String name, int baseDamage) {
        this.name = name;
        this.baseDamage = baseDamage;
        goldenApple = false;
        damageRecieved = 0;
    }


    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}
