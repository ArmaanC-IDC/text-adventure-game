package item.aggressive;

import item.Item;
import general.Game;
import rooms.Room;
import rooms.MobRoom;
import mobs.Mob;

public class Weapon extends Item{
    protected int baseDamage;
    protected int maxDamage;

    public static Weapon createWeapon(String type){
        switch (type.toLowerCase()) {
            case "dagger":
                return new Weapon("Dagger", 10, 12);
            case "sword":
                return new Weapon("Sword", 8, 15);
            case "axe":
                return new Weapon("Axe", 12, 20);
            case "hammer":
                return new Weapon("Hammer", 16, 25);
            case "sythe":
                return new Weapon("Sythe", 5, 50);
            case "trident":
                return new Weapon("Trident", 20, 25);
            default:
                return new Weapon("UNIMPLEMENTED: " + type, 0, 0);
        }
    }

    public Weapon(String name, int baseDamage, int maxDamage) {
        super(name, 20);
        this.baseDamage = baseDamage;
        this.maxDamage = maxDamage;
    }


    // public String getName() {
    //     return name;
    // }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getMaxDamage(){
        return maxDamage;        
    }

    // will return weapon when asked for type in player class
    public String getType() {
        return "weapon";
    }

    public boolean useItem(String[] args){
        Room currentRoom = Game.getGame().getCurrentRoom();
        if (!(currentRoom instanceof MobRoom)){
            Game.printText("You are not in a mob room, you cannot use that. ");
            return false;
        }
        MobRoom room = (MobRoom)currentRoom;
        if (room.getMobs().isEmpty()){
            Game.printText("There are no mobs in this mob room,");
            return false;
        }
        int damage = (int)(Math.random()*(maxDamage-baseDamage)) + baseDamage;
        for (Mob mob : ((MobRoom)room).getMobs()) {
            if (mob.getName().equalsIgnoreCase(args[3] + " " + args[4])){
                mob.takeDamage(damage);
                return true;
            }
        }
        Game.printText("There is no " + args[3] + " " + args[4] + " in this room.");
        return true;
    }
}