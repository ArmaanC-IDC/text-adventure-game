package item.passive;

import item.Item;
import item.ItemLoader;


public class Armour extends Item{
    protected int resistance;

    public static Armour createArmour(String type){
        switch (type.toLowerCase()) {
            case "leathersuit":
<<<<<<< HEAD
                return new Armour("leathersuit", 1);
            case "chainsuit":
                return new Armour("chainsuit", 2);
            case "diamondsuit":
                return new Armour("diamondsuit", 3);
=======
                return new Armour("leathersuit", ItemLoader.getItemConfig("leathersuit", "resistance"));
            case "chainsuit":
                return new Armour("chainsuit", ItemLoader.getItemConfig("chainsuit", "resistance"));
            case "diamondsuit":
                return new Armour("diamondsuit", ItemLoader.getItemConfig("diamondsuit", "resistance"));
>>>>>>> 124160eb0932ecb4034499a9ba8bed5569867faf
            default:
                return new Armour("UNIMPLEMENTED: " + type, 0);
        }
    }

    public Armour(String name, int resistance) {
        super(name, 15);
        this.resistance = resistance;
    }

    public int getResistance(){
        return resistance;
    }
}
