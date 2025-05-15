public class Sword extends Weapons{
    
    public Sword(){
        super("Sword",10);
    }

    public int damageDealt(){
        int damage = getBaseDamage() + (int) (Math.random() * 10) ;
        return damage;
    }



}
