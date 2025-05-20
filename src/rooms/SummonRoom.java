package rooms;

public class SummonRoom extends Room {
    public SummonRoom(String bossType, int roomCount, int row, int col) {
        super(bossType, roomCount, row, col);

        switch (bossType) {
            case "knightBossRoom":
                this.name = "Sanctum of the Fallen Knight";
                this.description = "A grand hall lies silent, its banners torn and faded. A summoning altar stands in the center, waiting. Put a Ranger Pendant and a Minotaur Horn in the altar to summon the cursed Knight who guards ancient secrets.";
                break;

            case "skeletonBossRoom":
                this.name = "Forest of Darkness";
                this.description = "This chamber reeks of decay. Rows of skeletal remains line the walls. An eerie pedestal glows faintly. Put Ancient Coins on the pedestal to awaken the Hollow-Eyed Ranger.";
                break;

            case "minotaurBossRoom":
                this.name = "Labyrinth's Heart";
                this.description = "You enter a circular stone arena etched with runes. A deep rumble resonates through the ground, and an eerie pedestal glows. Put Ancient Coins on the pedestal to summon the Minotaur Champion.";
                break;

            default:
                this.name = "Unknown Chamber";
                this.description = "An eerie, magical place with unknown purpose.";
        }
    }

    public void onPlayerEnter(){
        this.visited = true;
        System.out.println("IMPLEMENT THE SUMMONING ROOM");
    }
}
