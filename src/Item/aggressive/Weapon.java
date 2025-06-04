package item.aggressive;

import item.Item;
import general.Game;
import rooms.*;
import mobs.Mob;

import item.ItemLoader;

public class Weapon extends Item{ //child class of item
    protected int baseDamage;
    protected int maxDamage;

    public static Weapon createWeapon(String type){ //create weapon method and each close range weapon are objects
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

    public Weapon(String name, int baseDamage, int maxDamage) { // constructor with required parameters
        super(name, 10);
        this.baseDamage = baseDamage;
        this.maxDamage = maxDamage;
    }


    // public String getName() {
    //     return name;
    // }

    public int getBaseDamage() { //getter method
        return baseDamage;
    }

    public int getMaxDamage(){ //getter method
        return maxDamage;        
    }

    // will return weapon when asked for type in player class
    public String getType() { //used for if statements findweapon etc. shows that each object is a weapon
        return "weapon";
    }

    public boolean useItem(String[] args){ // Method for using an item (weapon) on a mob
    // Get the room the player is currently in
        Room currentRoom = Game.getGame().getCurrentRoom();
        if (args.length!=5){// Check that the command has exactly 5 words (e.g., "use dagger on split slime")
            Game.printText("Must be exactly 5 words. Use [weapon] on mob mob. Ex: use dagger on split slime");
            return false;
        }
        if (currentRoom instanceof MobRoom){// If the current room is a MobRoom (normal monster room)
            MobRoom room = (MobRoom)currentRoom;
            if (room.getMobs().isEmpty()){// If there are no mobs in this room, inform the player
                Game.printText("There are no mobs in this mob room,");
                return false;
            }
            for (Mob mob : ((MobRoom)room).getMobs()) {// Loop through each mob in the MobRoom
                if (mob.getName().equalsIgnoreCase(args[3] + " " + args[4])){ // Calculate damage: random between baseDamage and maxDamage, scaled by player's strength
                    int damage = (int)(((int)(Math.random()*(maxDamage-baseDamage)) + baseDamage) * (Game.getPlayer().getStrength()/100.0 + 1));
                    mob.takeDamage(damage);// Apply damage to the mob
                    return true;// Attack succeeded
                }
            }
        }
        
        else if (currentRoom instanceof SummonRoom){// If the current room is a SummonRoom 
            SummonRoom room = (SummonRoom)currentRoom;
            if (room.getMobs().isEmpty() && room.getBoss()==null){// If there are no mobs and no boss in this room, inform the player
                Game.printText("There are no mobs in this mob room,");
                return false;
            }
            for (Mob mob : ((SummonRoom)room).getMobs()) {// Loop through each mob in the SummonRoom
                if (mob.getName().equalsIgnoreCase(args[3] + " " + args[4])){// Check if the mob's name matches the last two words of the command
                    int damage = (int)(Math.random()*(maxDamage-baseDamage)) + baseDamage;// Calculate damage without strength multiplier for summoned mobs
                    mob.takeDamage(damage);// Apply damage to the mob
                    return true; // Attack succeeded
                }
            }
            if (room.getBoss().getName().equalsIgnoreCase(args[3] + " " + args[4])){// Check if the boss's name matches the last two words of the command
                int damage = (int)(((int)(Math.random()*(maxDamage-baseDamage)) + baseDamage) * (Game.getPlayer().getStrength()/100.0 + 1));// Calculate damage scaled by player's strength for the boss
                room.getBoss().takeDamage(damage);// Apply damage to the boss
                return true;
            }
            Game.printText("That mob does not exist in this room");
            return false;
        }else{// If the player is not in a MobRoom or SummonRoom, they cannot use weapons here
            Game.printText("You can only use weapons in a mob room and a summon room");
            return false;
        }

        if (args.length<5){// This check is redundant if args.length < 5 since we already verified args.length == 5 above,but it ensures the correct command format if somehow reached
            Game.printText("use weapon must be in format: use [weapon] on [mob]. Ex: use dagger on split slime");
            return false;
        }
        Game.printText("There is no " + args[3] + " " + args[4] + " in this room.");// If code reaches here, no matching mob was found in the room
        return false;
    }
}