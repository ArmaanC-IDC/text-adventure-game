import java.util.ArrayList;
import java.util.List;

import items.Item;

public class Player {
    private String currentRoomId;
    private List<Item> inventory;

    public Player(String startingRoomId) {
        this.currentRoomId = startingRoomId;
        this.inventory = new ArrayList<>();
    }

    // public String getCurrentRoomId() {
    //     return currentRoomId;
    // }

    // public void setCurrentRoomId(String roomId) {
    //     this.currentRoomId = roomId;
    // }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public boolean hasItem(String itemName) {
        return inventory.stream().anyMatch(i -> i.getName().equalsIgnoreCase(itemName));
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }
}