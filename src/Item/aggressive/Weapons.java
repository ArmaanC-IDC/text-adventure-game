package item.aggressive;

import item.Item;
import general.Game;
import rooms.Room;
import rooms.MobRoom;

public class Weapons extends Item{
    protected int baseDamage;
    protected int maxDamage;

    public Weapons(String name, int baseDamage, int maxDamage) {
        super(name);
        this.baseDamage = baseDamage;
        this.maxDamage = maxDamage;
    }


    public String getName() {
        return name;
    }

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

    public boolean useItem(){
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
        room.getMobs().get(0).takeDamage(damage);
        Game.printText("you dealt " + damage + " damage");
        return true;
    }
}
