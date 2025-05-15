package rooms;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import items.Item;

public class Room {
    private String id;
    private String name;
    private String description;
    private Map<String, String> exits;  // direction -> "row,col"
    private List<Item> items;
    private int[] coords;

    private static final int GRID_SIZE = 5;

    public Room(String id, String name, String description, Map<String, String> exits, List<Item> items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exits = exits;
        this.items = items;
    }

    public Room(String type, int roomCount, int row, int col){
        if (type.equals("startingRoom")){
            this.id = type;
        } else {
            this.id = Integer.toString(roomCount);
        }

        this.name = "NAME";
        this.description = "DESC";
        this.items = new ArrayList<Item>();
        this.coords = new int[]{row, col};
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
