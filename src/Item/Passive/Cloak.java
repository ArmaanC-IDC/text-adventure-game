package item.passive;
import item.Item;
import item.ItemLoader;

public class Cloak extends Armour{
    protected boolean probDodge;
    public Cloak(){
        super("Cloak",5); //cloak constructor extending to armour
        probDodge = (Math.random()<0.3); //probability of dodging an attack
        
    }

    public String getType(){ // a cloak for searching purposes
        return "cloak";
    }

    public boolean getProbDodge(){ //used to get if they dodged or not
        probDodge = (Math.random()<0.3);
        return probDodge;
    }

}
