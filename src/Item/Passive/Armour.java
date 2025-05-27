package item.Passive;

import item.Item;
import general.Game;
import rooms.Room;
import rooms.MobRoom;
import mobs.Mob;

public class Armour extends Item{
    protected int resistance;

    public static Armour createArmour(String type){
        switch (type.toLowerCase()) {
            case "leathersuit":
                return new Armour("leathersuit", 5);
            case "chainsuit":
                return new Armour("chainsuit", 8);
            case "diamondsuit":
                return new Armour("diamondsuit", 12);
            default:
                return new Armour("UNIMPLEMENTED: " + type, 0);
        }
    }

    public Armour(String name, int resistance) {
        super(name, 15);
        this.resistance = resistance;
    }

    
}
