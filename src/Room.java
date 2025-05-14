import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Room {
    public static int exitsPerRoom = 2;
    public static List<String> roomTypes = Arrays.asList("corridor", "corridor", "corridor", "corridor", "treasure_room");
    public static int roomCount = 0;

    private String id;
    private String name;
    private String description;
    private Map<String, String> exits; // direction → roomId
    private List<Item> items;

    // public Room(String id, String name, String description, Map<String, String> exits, List<Item> items) {
    //     this.id = id;
    //     this.name = name;
    //     this.description = description;
    //     this.exits = exits;
    //     this.items = items;
    // }

    //for the starting room
    public Room(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exits = new HashMap<String, String>();
        this.items = new ArrayList<Item>();


        List<String> directions = new ArrayList<>(Arrays.asList("north", "east", "south", "west"));
        Collections.shuffle(directions);
        for (int i = 0; i < Room.exitsPerRoom; i++) {
            exits.put(directions.get(i), "");
        }

        this.items.add(new Item("IMPLEMENT ITEMS", "IMPLEMENT ITEM ADDING", "DESC"));
    }

    //for all generated rooms
    public Room(String lastRoomId, String entranceDir) {
        //init type
        Collections.shuffle(roomTypes);
        String type = roomTypes.get(0);

        //init id
        this.id = type + roomCount;
        roomCount++;

        //init name and desc
        if (type.equals("corridor")) {
            //get the name
            List<String> names = Arrays.asList("Corridor", "Passage", "Hall");
            Collections.shuffle(names);

            //get the adjective
            List<String> adjectives = Arrays.asList("Dark", "Gloomy", "Damp");
            Collections.shuffle(names);
            String adjective = adjectives.get(0);

            //combine them
            name = adjective + " " + names.get(0);
            description = "The narrow path stretches endlessly, the walls are covered in moss and mold.";
        } else { // treasure_room
            name = "Treasure Room ";
            description = "You see gleaming treasures scattered across the floor, gold and jewels everywhere.";
        }

        //init exits
        this.exits = new HashMap<String, String>();
        this.exits.put(entranceDir, lastRoomId);

        List<String> directions = new ArrayList<>(Arrays.asList("north", "east", "south", "west"));
        directions.remove(entranceDir); 
        Collections.shuffle(directions);
        for (int i = 0; i < Room.exitsPerRoom-1; i++) {
            exits.put(directions.get(i), "");
        }

        //add items
        this.items = new ArrayList<Item>();
        this.items.add(new Item("IMPLEMENT ITEMS", "IMPLEMENT ITEM ADDING", "DESC"));

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return description;
    }

    public Map<String, String> getExits() {
        return exits;
    }

    public List<Item> getItems() {
        return items;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void addItem(Item item) {
        items.add(item);
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
            // Remove trailing comma and space
            sb.setLength(sb.length() - 2);
            sb.append(".\n");
        }

        if (!exits.isEmpty()) {
            sb.append("Exits: ");
            for (String direction : exits.keySet()) {
                sb.append(direction).append(", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append(".\n");
        }

        return sb.toString();
    }
}
