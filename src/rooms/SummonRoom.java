package rooms;
import general.Game;
import player.Player;
import item.Item;
import mobs.*;

import java.util.ArrayList;

import java.util.ArrayList;

public class SummonRoom extends Room {
    private Mob boss;
    private ArrayList<Mob> mobs;

    public SummonRoom(String bossType, int roomCount, int row, int col) {
        super(bossType, roomCount, row, col);

        //based on the boss type, name and description is different
        switch (bossType) {
            case "knightBossRoom":
                this.name = "Sanctum of the Fallen Knight";
                this.description = "A grand hall lies silent, its banners torn and faded. A summoning altar stands in the center, waiting. Put a Ranger Pendant and a Minotaur Horn in the altar to summon the cursed Knight who guards ancient secrets.";
                break;

            case "rangerBossRoom":
                this.name = "Forest of Darkness";
                this.description = "This chamber reeks of decay. Rows of arrows line the walls. An eerie pedestal glows faintly. Put Ancient Coins on the pedestal to awaken the Hollow-Eyed Ranger.";
                break;

            case "minotaurBossRoom":
                this.name = "Labyrinth's Heart";
                this.description = "You enter a circular stone arena etched with runes. A deep rumble resonates through the ground, and an eerie pedestal glows. Put Ancient Coins on the pedestal to summon the Minotaur Champion.";
                break;

            default:
                this.name = "UNIMPLEMENTED TYPE";
                this.description = bossType;
        }
        this.mobs = new ArrayList<Mob>();
    }

    //for getLongDescription, add what boss is in the room
    public String getLongDescription(){
        if (boss!=null){
            return super.getLongDescription() + "Boss: " + boss.getName() + ". \n";
        }
        return super.getLongDescription();
    }

    public void onPlayerTurn(Player player){
        for (int i = mobs.size()-1; i>=0; i--) {
            //if the mob is not alive, remove it
            if (!mobs.get(i).isAlive()){
                mobs.remove(i);
                continue;
            }
            //call mob preformAttack
            mobs.get(i).performAttack(player);
        }
        if (boss!= null && boss.isAlive()){
            boss.performAttack(player);
        }else{
            boss = null;
        }
    }

    public ArrayList<Mob> getMobs() { return mobs; }
    public Mob getBoss() { return boss; }

    //returns true/false weather it worked
    public boolean summon(){
        ArrayList<Item> inventory = Game.getPlayer().getInventory();
        switch (type){
            case "rangerBossRoom", "minotaurBossRoom": //for ranger boss and minotaur boss (both need coins to summon)
                //get how many ancient coins the player has
                int numCoins = 0;
                for (Item item : inventory) {
                    if (item.getName().equalsIgnoreCase("coin")){
                        numCoins++;
                    }
                }

                int coinsNeeded = RoomsLoader.getSummonRoomConfig("neededCoins");
                //if player has enough, summon boss
                if (numCoins>=coinsNeeded){
                    //remove coinsNeeded coins
                    int numRemoved = 0;
                    for (int i = inventory.size()-1; i >= 0; i--) {
                        Item item = inventory.get(i);
                        if (item.getName().equalsIgnoreCase("coin") && numRemoved<coinsNeeded){
                            inventory.remove(i);
                            numRemoved++;
                        }
                    }

                    if (this.type=="rangerBossRoom"){
                        boss = new HollowEyedRanger();
                    } else {
                        boss = new IronhornMinotaur();
                    }
                    return true;
                }else{ //if player does not have enough coins
                    Game.printText("You need " + coinsNeeded + " coins to do this");
                    Game.printText("You have " + numCoins + " coins");
                    return false;
                }
            case "knightBossRoom": //for knight boss (needs ranger pendant and minotaur horn)
                boolean hasPendant = false;
                boolean hasHorn = false;
                
                //check if player has pendant and horn
                for (Item item : inventory) {
                    if (item.getName().equalsIgnoreCase("horn")){
                        hasHorn = true;
                    }
                    if (item.getName().equalsIgnoreCase("pendant")){
                        hasPendant = true;
                    }
                }
                if (hasHorn && hasPendant){
                    //summon the boss and call it's preform attack method
                    boss = new CorruptedKnight();
                    boss.performAttack(Game.getPlayer());

                    //remove horn and pendant
                    for (int i = inventory.size()-1; i >= 0; i--) {
                        Item item = inventory.get(i);
                        if (item.getName().equals("horn") && hasHorn){
                            System.out.println("removed horn");
                            inventory.remove(i);
                            hasHorn = false;
                        }
                        if (item.getName().equals("pendant") && hasPendant){
                            System.out.println("removed pendant");
                            inventory.remove(i);
                            hasPendant = false;
                        }
                    }
                //if does not have horn and pendant
                } else if (hasHorn){
                    Game.printText("You need a pandant obtained from the hollow-eyed ranger");
                } else if (hasPendant){
                    Game.printText("You need a horn obtained from the minotaur");
                } else {
                    Game.printText("You need a pendant from the ranger and a horn from the minotaur");
                }
            default: //should never get here, just for safety
                return false;
        }
    }
}
