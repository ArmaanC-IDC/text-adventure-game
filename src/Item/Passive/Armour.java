package item.passive;

import item.Item;
import item.ItemLoader;


public class Armour extends Item{
    protected int resistance;

    public static Armour createArmour(String type){
        switch (type.toLowerCase()) {
            case "leathersuit":
                return new Armour("leathersuit", ItemLoader.getItemConfig("leathersuit", "resistance"));
            case "chainsuit":
                return new Armour("chainsuit", ItemLoader.getItemConfig("chainsuit", "resistance"));
            case "diamondsuit":
                return new Armour("diamondsuit", ItemLoader.getItemConfig("diamondsuit", "resistance"));
            default:
                return new Armour("UNIMPLEMENTED: " + type, 0);
        }
    }

    public Armour(String name, int resistance) {
        super(name, resistance);
        this.resistance = resistance;
    }

    public int getResistance(){
        return resistance;
    }
}
