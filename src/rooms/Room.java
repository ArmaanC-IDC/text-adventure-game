package rooms;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import item.Item;
import player.Player;
import item.aggressive.*;
import item.Passive.*;

import java.util.HashMap;

public class Room {
    protected String id;
    protected String name;
    protected String description;
    protected String type;
    protected Map<String, String> exits; //direction -> x, y
    protected Map<String, Boolean> blockedExits; //direction -> bool (true/false, isblocked)
    protected List<Item> items;
    protected boolean visited;

    private final int GRID_SIZE;

    //create a room of a given type. 
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

    //called when creating a room directly (no child classes)
    public Room(String type, int roomCount, int row, int col, String name, String description) {
        this.type = type;
        this.items = new ArrayList<>();
        if (type.equals("startingRoom")){
            this.id = type;
            this.items.add(Weapon.createWeapon("dagger"));
        }else{
            this.id = Integer.toString(roomCount);
        }
        this.exits = new HashMap<>();
        this.blockedExits = new HashMap<String, Boolean>();
        this.name = name;
        this.description = description;
        GRID_SIZE = RoomsLoader.getRoomConfig("gridSize");
        initExits(row, col);
    }

    //child classes call this constructor and set name and desc
    public Room(String type, int roomCount, int row, int col) {
        this.type = type;
        this.items = new ArrayList<>();
        this.id = Integer.toString(roomCount);
        this.exits = new HashMap<>();
        this.blockedExits = new HashMap<String, Boolean>();
        GRID_SIZE = RoomsLoader.getRoomConfig("gridSize");
        initExits(row, col);
    }

    private void initExits(int row, int col) {
        if (row > 0) {
            exits.put("north", (row - 1) + "," + col);
            blockedExits.put("north", false);
        }
        if (col < GRID_SIZE - 1) {
            exits.put("east", row + "," + (col + 1));
            blockedExits.put("east", false);
        }
        if (row < GRID_SIZE - 1) {
            exits.put("south", (row + 1) + "," + col);
            blockedExits.put("south", false);
        }
        if (col > 0) {
            exits.put("west", row + "," + (col - 1));
            blockedExits.put("west", false);
        }        
    }

    //runs every player turn. update elements like mobs here. Do nothing for standard room
    public void onPlayerTurn(Player player){

    }

    //get a random string from a string array. used to randomize name/description
    protected static String getRandomFromArray(String[] options) {
        return options[(int)(Math.random() * options.length)];
    }

    //what do do when the player enters the room
    public void onPlayerEnter(Player player) {
        this.visited = true;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public Map<String, String> getExits() { return exits; }
    public List<Item> getItems() { return items; }
    public boolean getVisited() { return visited ; }
    public Map<String, Boolean> getBlockedExits(){ return blockedExits; }

    //description shown upon room enter
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

    //toString method for debugging
    public String toString() {
        return name + " " + description + " " + id;
    }
}
