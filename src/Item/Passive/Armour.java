package item.passive;

import item.Item;
import item.ItemLoader;


public class Armour extends Item{
    protected int resistance;

    public static Armour createArmour(String type){ //create armour method similar to weapon
        switch (type.toLowerCase()) {
            case "leathersuit":
                return new Armour("leathersuit", ItemLoader.getItemConfig("leathersuit", "resistance")); //leather suit creation
            case "chainsuit":
                return new Armour("chainsuit", ItemLoader.getItemConfig("chainsuit", "resistance"));  // chain suit creation
            case "diamondsuit":
                return new Armour("diamondsuit", ItemLoader.getItemConfig("diamondsuit", "resistance")); // diamond suit creation
            default:
                return new Armour("UNIMPLEMENTED: " + type, 0); // handle unsupported armour types (currently returns a placeholder with 0 resistance)
        }
    }

    public Armour(String name, int resistance) { // add resistance for calculation purposes
        super(name, 15);
        this.resistance = resistance;
    }

    public int getResistance(){ //getter method for calculation purposes
        return resistance;
    }

    public String getType(){ //getter method for searching purposes
        return "armour";
    }
}
