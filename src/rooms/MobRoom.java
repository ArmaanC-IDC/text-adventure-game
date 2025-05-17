package rooms;

import mobs.Mob;

public class MobRoom extends Room {
    private Mob mob;

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
    }

    public Mob getMob() {
        return mob;
    }

    public void setMob(Mob mob) {
        this.mob = mob;
    }
}
