package item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Global item pool that all mobs share
public class ItemPool {
    private static List<Item> availableItems = new ArrayList<>();
    
    // Add an item to the global pool
    public static void addItem(Item item) {
        availableItems.add(item);
    }
    
    // Get all available items
    public static List<Item> getAvailableItems() {
        return availableItems;
    }
    
    // Get a random item from the pool
    public static Item getRandomItem() {
        if (availableItems.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(availableItems.size());
        return availableItems.get(index).copy();
    }
}