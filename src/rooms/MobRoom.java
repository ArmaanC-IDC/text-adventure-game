package rooms;

import mobs.CrumblingSkeleton;
import mobs.GoblinScavenger;
import mobs.Mob;
import mobs.SplitSlime;
import player.Player;

import java.util.ArrayList;

public class MobRoom extends Room {
    private ArrayList<Mob> mobs;
    private final int maxMobs = 4;
    private final int minMobs = 1;

    public MobRoom(int roomCount, int row, int col) {
        super("mobRoom", roomCount, row, col);

        //set name to random adjective + random noun
        this.name = getRandomFromArray(new String[]{
            "Infested ", "Monster ", "Blood-Stained "
        }) + getRandomFromArray(new String[]{
            "Hall", "Nest", "Barracks"
        });

        //set description to combine two phrases.
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

    //when the player makes their turn, check for mob death and if all dead unlock exits
    public void onPlayerTurn(Player player){
        for (int i = mobs.size()-1; i>=0; i--) {
            if (!mobs.get(i).isAlive()){
                mobs.remove(i);
                continue;
            }
            mobs.get(i).performAttack(player);
        }

        if (mobs.size()==0){
            this.blockedExits.put("north", false);
            this.blockedExits.put("east", false);
            this.blockedExits.put("south", false);
            this.blockedExits.put("west", false);
        }
    }

    //when the player enters, block all exits and summon mobs.
    public void onPlayerEnter(Player player){
        this.visited = true;
        int numMobs = (int)(Math.random()*(maxMobs - minMobs)) + minMobs;
        for (int i = 0; i < numMobs; i++) {
            int mob = (int)Math.random()*3;
            switch (mob){
                case 0:
                    mobs.add(new CrumblingSkeleton());
                    break;
                case 1:
                    mobs.add(new GoblinScavenger());
                    break;
                case 2:
                    mobs.add(new SplitSlime());
                    break;
            }
        }
        this.blockedExits.put("north", true);
        this.blockedExits.put("east", true);
        this.blockedExits.put("south", true);
        this.blockedExits.put("west", true);
    }

    public ArrayList<Mob> getMobs() { return mobs; }

    //when you enter a mob room show the list of mobs there as well as other things
    public String getLongDescription(){
        String mobsString = "Mobs: \n";
        for (Mob mob : mobs) {
            mobsString += "   " + mob + "\n";
        }
        return super.getLongDescription() + mobsString;
    }
}
