package rooms;

import mobs.CrumblingSkeleton;
import mobs.GoblinScavenger;
import mobs.Mob;
import mobs.SplitSlime;
import player.Player;

import java.util.ArrayList;

public class MobRoom extends Room {
    private ArrayList<Mob> mobs;

    public MobRoom(int roomCount, int row, int col) {
        super("mobRoom", roomCount, row, col);
        this.name = getRandomFromArray(new String[]{
            "Infested ", "Monster ", "Blood-Stained "
        }) + getRandomFromArray(new String[]{
            "Hall", "Nest", "Barracks"
        });
        this.description = getRandomFromArray(new String[]{
            "Growls echo from the shadows. ",
            "You sense movement. ",
            "The floor is slick with something you hope isn't blood. "
        }) + getRandomFromArray(new String[]{
            "Hostile creatures roam here, drawn by the dungeon's dark energy.",
            "Clawed feet scrape stone as monsters lie in wait.",
            "The beasts that live here won't be pleased to see you."
        });
        mobs = new ArrayList<Mob>();
    }

    public void 
    onPlayerTurn(){
        for (int i = mobs.size()-1; i>=0; i--) {
            if (!mobs.get(i).isAlive()){
                mobs.remove(i);
            }
        }

        if (mobs.size()==0){
            this.blockedExits.put("north", false);
            this.blockedExits.put("east", false);
            this.blockedExits.put("south", false);
            this.blockedExits.put("west", false);
        }
    }

    public String onPlayerEnter(Player player){
        this.visited = true;
        mobs.add(new GoblinScavenger());
        mobs.add(new SplitSlime());
        mobs.add(new CrumblingSkeleton());
        this.blockedExits.put("north", true);
        this.blockedExits.put("east", true);
        this.blockedExits.put("south", true);
        this.blockedExits.put("west", true);
        return "";
    }


    public ArrayList<Mob> getMobs() {
        return mobs;
    }

    public String getLongDescription(){
        String mobsString = "Mobs: \n";
        for (Mob mob : mobs) {
            mobsString += "   " + mob + "\n";
        }
        return super.getLongDescription() + mobsString;
    }
}
