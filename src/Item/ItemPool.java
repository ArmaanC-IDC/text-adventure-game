package item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import item.aggressive.*;
import item.Passive.*;
import java.util.function.Supplier;

// Global item pool that all mobs share
public class ItemPool {
    private static List<Supplier<Item>> availableItems = new ArrayList<>();
    private static Random random = new Random();
    
    static {
        availableItems.add(() -> new HealthPotion());
        availableItems.add(() -> new StrengthPotion());
        availableItems.add(() -> new Coins());
        availableItems.add(() -> Weapon.createWeapon("dagger"));
        availableItems.add(() -> Weapon.createWeapon("sword"));
        availableItems.add(() -> Weapon.createWeapon("axe"));
        availableItems.add(() -> Weapon.createWeapon("hammer"));
        availableItems.add(() -> Weapon.createWeapon("sythe"));
        availableItems.add(() -> Weapon.createWeapon("trident"));

        // ADD REST OF ITEMS HERE
    }
    
    // Get a random item from the pool
    public static Item getRandomItem() {
        if (availableItems.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(availableItems.size());
        return availableItems.get(index).get();
    }

    // public static Item getRandomItem() {
    //     return Weapon.createWeapon("trident");
    // }
}