package item.aggressive;

import item.Item;
import general.Game;
import rooms.*;
import mobs.Mob;

import item.ItemLoader;

public class Weapon extends Item{
    protected int baseDamage;
    protected int maxDamage;

    public static Weapon createWeapon(String type){
        switch (type.toLowerCase()) {
            case "dagger":
                return new Weapon("Dagger", 
                    ItemLoader.getItemConfig("dagger", "minDamage"), 
                    ItemLoader.getItemConfig("dagger", "maxDamage")
                );
            case "sword":
                return new Weapon("Sword", 
                    ItemLoader.getItemConfig("sword", "minDamage"), 
                    ItemLoader.getItemConfig("sword", "maxDamage")
                );
            case "axe":
                return new Weapon("Axe", 
                    ItemLoader.getItemConfig("axe", "minDamage"), 
                    ItemLoader.getItemConfig("axe", "maxDamage")
                );
            case "hammer":
                return new Weapon("Hammer", 
                    ItemLoader.getItemConfig("hammer", "minDamage"), 
                    ItemLoader.getItemConfig("hammer", "maxDamage")
                );
            case "sythe":
                return new Weapon("Sythe", 
                    ItemLoader.getItemConfig("sythe", "minDamage"), 
                    ItemLoader.getItemConfig("sythe", "maxDamage")
                );
            case "trident":
                return new Weapon("Trident", 
                    ItemLoader.getItemConfig("trident", "minDamage"), 
                    ItemLoader.getItemConfig("trident", "maxDamage")
                );
            default:
                return new Weapon("UNIMPLEMENTED: " + type, 0, 0);
        }
    }

    public Weapon(String name, int baseDamage, int maxDamage) {
        super(name, 10);
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
        if (args.length!=5){
            Game.printText("Must be exactly 5 words. Use [weapon] on mob mob. Ex: use dagger on split slime");
            return false;
        }
        if (currentRoom instanceof MobRoom){
            MobRoom room = (MobRoom)currentRoom;
            if (room.getMobs().isEmpty()){
                Game.printText("There are no mobs in this mob room,");
                return false;
            }
            for (Mob mob : ((MobRoom)room).getMobs()) {
                if (mob.getName().equalsIgnoreCase(args[3] + " " + args[4])){
                    int damage = (int)(((int)(Math.random()*(maxDamage-baseDamage)) + baseDamage) * (Game.getPlayer().getStrength()/100.0 + 1));
                    mob.takeDamage(damage);
                    return true;
                }
            }
        }
        
        else if (currentRoom instanceof SummonRoom){
            SummonRoom room = (SummonRoom)currentRoom;
            if (room.getMobs().isEmpty() && room.getBoss()==null){
                Game.printText("There are no mobs in this mob room,");
                return false;
            }
            for (Mob mob : ((SummonRoom)room).getMobs()) {
                if (mob.getName().equalsIgnoreCase(args[3] + " " + args[4])){
                    int damage = (int)(Math.random()*(maxDamage-baseDamage)) + baseDamage;
                    mob.takeDamage(damage);
                    return true;
                }
            }
            if (room.getBoss().getName().equalsIgnoreCase(args[3] + " " + args[4])){
                int damage = (int)(((int)(Math.random()*(maxDamage-baseDamage)) + baseDamage) * (Game.getPlayer().getStrength()/100.0 + 1));
                room.getBoss().takeDamage(damage);
                return true;
            }
            Game.printText("That mob does not exist in this room");
            return false;
        }else{
            Game.printText("You can only use weapons in a mob room and a summon room");
            return false;
        }

        if (args.length<5){
            Game.printText("use weapon must be in format: use [weapon] on [mob]. Ex: use dagger on split slime");
            return false;
        }
        Game.printText("There is no " + args[3] + " " + args[4] + " in this room.");
        return false;
    }
}