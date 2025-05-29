package item.passive;

import item.Item;


public class Armour extends Item{
    protected int resistance;

    public static Armour createArmour(String type){
        switch (type.toLowerCase()) {
            case "leathersuit":
                return new Armour("leathersuit", 1);
            case "chainsuit":
                return new Armour("chainsuit", 2);
            case "diamondsuit":
                return new Armour("diamondsuit", 3);
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
