package item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import item.aggressive.*;
import item.Passive.*;

// Global item pool that all mobs share
public class ItemPool {
    private static List<Item> availableItems = new ArrayList<>();
    private static Random random = new Random();
    
    static {
        addItem(new HealthPotion());
        addItem(new StrengthPotion());
        addItem(Weapon.createWeapon("dagger"));
        addItem(Weapon.createWeapon("sword"));
        addItem(Weapon.createWeapon("axe"));
        addItem(Weapon.createWeapon("hammer"));
        addItem(Weapon.createWeapon("sythe"));
        addItem(Weapon.createWeapon("trident"));

        // ADD REST OF ITEMS HERE
    }
    
    // Add an item to the global pool
    public static void addItem(Item item) {
        availableItems.add(item);
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