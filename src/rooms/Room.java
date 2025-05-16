package rooms;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import items.Item;
import mobs.Mob;

public class Room {
    private String id;
    private String name;
    private String description;
    private String type;
    private Mob mob;
    private Map<String, String> exits;  // direction -> "row,col"
    private List<Item> items;

    private static final int GRID_SIZE = 5;
    private static final int TRAP_ROOM_CHANCE = 90;
    private static final int TRAP_ROOM_DAMAGE_MIN = 5;
    private static final int TRAP_ROOM_DAMAGE_MAX = 10;

    public Room(String type, int roomCount, int row, int col){
        this.type = type;

        if (type.equals("startingRoom")){
            this.id = type;
        } else {
            this.id = Integer.toString(roomCount);
        }

        switch (type) {
            case "startingRoom":
                this.name = "Starting Room";
                this.description = "You find yourself in a dimly lit stone chamber. The air is stale, and ancient dust clings to every surface. This is where your journey begins.";
                break;

            case "knightBossRoom":
                this.name = "Sanctum of the Fallen Knight";
                this.description = "A grand hall lies silent, its banners torn and faded. A summoning altar stands in the center, waiting. Put a Ranger Pendant and a Minotaur Horn in the altar to summon the cursed Knight who guards ancient secrets.";
                break;

            case "skeletonBossRoom":
                this.name = "Forest of Darkness";
                this.description = "This chamber reeks of decay. Rows of skeletal remains line the walls. An eerie pedestal glows faintly. Put Ancient Coins on the pedastal to awaken the Hollow-Eyed Ranger.";
                break;

            case "minotaurBossRoom":
                this.name = "Labyrinth's Heart";
                this.description = "You enter a circular stone arena etched with runes. A deep rumble resonates through the ground, and an eerie pedastal glows. Put Ancient Coins on the pedastal to summon the Minotaur Champion.";
                break;

            case "mobRoom":
                this.name = getRandomFromArray(new String[]{
                    "Infested ", "Monster ", "Blood-Stained "
                }) + getRandomFromArray(new String[]{
                    "Hall", "Nest", "Barracks"
                })
                ;
                this.description = getRandomFromArray(new String[]{
                    "Growls echo from the shadows. ",
                    "You sense movement. ",
                    "The floor is slick with something you hope isn't blood. "
                }) + getRandomFromArray(new String[]{
                    "Hostile creatures roam here, drawn by the dungeon's dark energy.",
                    "Clawed feet scrape stone as monsters lie in wait.",
                    "The beasts that live here won't be pleased to see you."
                });
                break;

            case "trapRoom":
                this.name = getRandomFromArray(new String[]{
                    "Hall of Snares", "Trigger Chamber", "Trap-Laden Passage"
                });
                this.description = getRandomFromArray(new String[]{
                    "Pressure plates and hidden wires suggest deadly traps await. Tread lightly.",
                    "The air here is too quiet. Something isn't right.",
                    "You spot grooves in the floor; this corridor may be rigged with mechanisms."
                });
                break;

            case "treasureRoom":
                this.name = getRandomFromArray(new String[]{
                    "Hidden ", "Treasure ", "Glittering "
                }) + getRandomFromArray(new String[]{
                    "Vault", "Cache", "Chamber"
                });
                this.description = getRandomFromArray(new String[]{
                    "A chest gleams invitingly, bathed in a golden light. Could it be genuine treasure?",
                    "Treasure sparkles ahead, but nothing in this place is free.",
                    "An ornate box sits atop a pedestal. Riches or ruin may lie within."
                });
                break;

            case "corridor":
                this.name = getRandomFromArray(new String[]{
                    "Winding Passage", "Dusty Hallway", "Stone Tunnel"
                });
                this.description = getRandomFromArray(new String[]{
                    "A narrow hallway stretches before you, dimly lit and eerily quiet.",
                    "This passage connects rooms, but the silence suggests something unseen watches.",
                    "Old torches flicker on the walls as the corridor twists out of sight."
                });
                break;
        }

        this.items = new ArrayList<Item>();

        this.exits = new HashMap<>();
        initExits(row, col);
    }

    private void initExits(int row, int col){
        // Initialize exits based on current position and grid bounds
        // Format of value: "row,col"

        // North exit (row-1, col)
        if (row > 0) {
            exits.put("north", (row - 1) + "," + col);
        }

        // East exit (row, col+1)
        if (col < GRID_SIZE - 1) {
            exits.put("east", row + "," + (col + 1));
        }

        // South exit (row+1, col)
        if (row < GRID_SIZE - 1) {
            exits.put("south", (row + 1) + "," + col);
        }

        // West exit (row, col-1)
        if (col > 0) {
            exits.put("west", row + "," + (col - 1));
        }
    }

    private String getRandomFromArray(String[] options) {
        int index = (int)(Math.random() * options.length);
        return options[index];
    }
    
    public String onPlayerEnter(){
        switch (this.type){
            case "trapRoom":
                if (Math.random()<TRAP_ROOM_CHANCE/100.0){
                    int damage = (int)(Math.random()*(TRAP_ROOM_DAMAGE_MAX-TRAP_ROOM_DAMAGE_MIN)) + TRAP_ROOM_DAMAGE_MIN;
                    System.out.println("TODO: APPLY TRAP ROOM DAMAGE");
                    return getRandomFromArray(new String[]{
                        "A blade flies out from the walls and hits you dealing " + damage + " damage.",
                        "A pressure plate clicks. Darts shoot from hidden holes, piercing you for " + damage + " damage.",
                        "You feel the floor sink slightly. Flames erupt beneath your feet, burning and dealing " + damage + " damage.",
                        "A stone above you shifts. A spiked log swings down, slamming into you dealing " + damage + " damage.",
                    });
                } else {
                    return getRandomFromArray(new String[]{
                        "A blade flies out from the walls and hits you and narrowly misses.",
                        "A pressure plate clicks. Darts shoot from hidden holes and barely miss.",
                        "You feel the floor sink slightly. Flames erupt beneath your feet but you jump back in time.",
                        "A stone above you shifts. A spiked log swings down narrowly missing you.",
                    }) + " Next time you may not be so lucky.";
                }
            default:
                return "";
        }
    }
    // getters and toString() ...

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getExits() {
        return exits;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getLongDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        sb.append(description).append("\n");

        if (!items.isEmpty()) {
            sb.append("You see: ");
            for (Item item : items) {
                sb.append(item.getName()).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append(".\n");
        }

        if (!exits.isEmpty()) {
            sb.append("Exits: ");
            for (String dir : exits.keySet()) {
                sb.append(dir).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append(".\n");
        }

        return sb.toString();
    }

    public String toString(){
        return this.name + " " + this.description + " " + this.id;
    }
}
