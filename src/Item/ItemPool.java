package item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import item.aggressive.*;
import item.Passive.*;

import java.util.function.Supplier;

// Global item pool that all mobs share
public class ItemPool {
    private static List<Supplier<Item>> mobDropItems = new ArrayList<>();
    private static List<Supplier<Item>> treasureRoomItems = new ArrayList<>();
    private static Random random = new Random();
    
    static {
        // Add all items to mobDropItems
        mobDropItems.add(() -> new HealPotion());
        mobDropItems.add(() -> new StrengthPotion());
        mobDropItems.add(() -> new Coins());
        mobDropItems.add(() -> Weapon.createWeapon("dagger"));
        mobDropItems.add(() -> Weapon.createWeapon("sword"));
        mobDropItems.add(() -> Weapon.createWeapon("axe"));
        mobDropItems.add(() -> Weapon.createWeapon("hammer"));
        mobDropItems.add(() -> Weapon.createWeapon("sythe"));
        mobDropItems.add(() -> Weapon.createWeapon("trident"));
        mobDropItems.add(() -> new Clinic());
        mobDropItems.add(() -> new GoldenApple());
        mobDropItems.add(() -> Armour.createArmour("chainsuit"));
        mobDropItems.add(() -> Armour.createArmour("diamondarmour"));

        // Add all items to treasureRoomItems
        treasureRoomItems.add(() -> new HealPotion());
        treasureRoomItems.add(() -> new StrengthPotion());
        treasureRoomItems.add(() -> Weapon.createWeapon("sword"));
        treasureRoomItems.add(() -> Weapon.createWeapon("axe"));
        treasureRoomItems.add(() -> Weapon.createWeapon("sythe"));
        treasureRoomItems.add(() -> new Clinic());
        treasureRoomItems.add(() -> new GoldenApple());
        treasureRoomItems.add(() -> Armour.createArmour("leathersuit"));
    }
    
    // Get a random item from the pool
    public static Item getRandomMobDrop() {
        if (mobDropItems.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(mobDropItems.size());
        return mobDropItems.get(index).get();
    }

    public static Item getRandomTreasureRoomItem() {
        if (treasureRoomItems.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(treasureRoomItems.size());
        return treasureRoomItems.get(index).get();
    }
}