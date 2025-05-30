package item.passive;
import item.Item;
import item.ItemLoader;

public class Cloak extends Armour{
    protected boolean probDodge;
    public Cloak(){
        super("Cloak",5);
        probDodge = (Math.random()<0.3);
        
    }

    public String getType(){
        return "cloak";
    }

    public boolean getProbDodge(){
        probDodge = (Math.random()<0.3);
        return probDodge;
    }

}
