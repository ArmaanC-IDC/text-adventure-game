package rooms;

import java.nio.channels.IllegalChannelGroupException;
import java.util.*;
import item.Item;
import player.Player;

public class Room {
    protected String id;
    protected String name;
    protected String description;
    protected String type;
    protected Map<String, String> exits;
    protected List<Item> items;
    protected boolean visited;

    private static final int GRID_SIZE = 5;

    public static Room createRoom(String type, int roomCount, int row, int col){
        switch (type){
            case "knightBossRoom", "minotaurBossRoom", "rangerBossRoom":
            return new SummonRoom(type, roomCount, row, col);
            case "trapRoom":
                return new TrapRoom(roomCount, row, col);
            case "mobRoom":
                return new MobRoom(roomCount, row, col);
            case "treasureRoom":
                return new TreasureRoom(roomCount, row, col);
            case "corridor":
                String name = getRandomFromArray(new String[]{
                    "Winding Passage", "Dusty Hallway", "Stone Tunnel"
                });
                String description = getRandomFromArray(new String[]{
                    "A narrow hallway stretches before you, dimly lit and eerily quiet.",
                    "This passage connects rooms, but the silence suggests something unseen watches.",
                    "Old torches flicker on the walls as the corridor twists out of sight."
                });
                return new Room(type, roomCount, row, col, name, description);
            default:
                return new Room(type, roomCount, row, col, "Starting Room", 
                    "You find yourself in a dimly lit stone chamber. The air is stale, and ancient dust clings to every surface. This is where your journey begins."
                );

        }
    }

    public Room(String type, int roomCount, int row, int col, String name, String description) {
        this.type = type;
        if (type.equals("startingRoom")){
            this.id = type;
        }else{
            this.id = Integer.toString(roomCount);
        }
        this.items = new ArrayList<>();
        this.exits = new HashMap<>();
        this.name = name;
        this.description = description;
        initExits(row, col);
    }

    public Room(String type, int roomCount, int row, int col) {
        this.type = type;
        if (type.equals("startingRoom")){
            this.id = type;
        }else{
            this.id = Integer.toString(roomCount);
        }
        this.items = new ArrayList<>();
        this.exits = new HashMap<>();
        initExits(row, col);
    }

    protected void initExits(int row, int col) {
        if (row > 0) exits.put("north", (row - 1) + "," + col);
        if (col < GRID_SIZE - 1) exits.put("east", row + "," + (col + 1));
        if (row < GRID_SIZE - 1) exits.put("south", (row + 1) + "," + col);
        if (col > 0) exits.put("west", row + "," + (col - 1));
    }

    protected static String getRandomFromArray(String[] options) {
        return options[(int)(Math.random() * options.length)];
    }

    public String onPlayerEnter(Player player) {
        this.visited = true;
        return "";  // Default behavior
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public Map<String, String> getExits() { return exits; }
    public List<Item> getItems() { return items; }
    public boolean getVisited() { return visited ; }

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

    public String toString() {
        return name + " " + description + " " + id;
    }
}
