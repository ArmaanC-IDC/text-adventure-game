import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.lang.Math.*;
import java.util.Random;


public class Player {
    // Position
    private String currentRoomId;
    
    // Base stats
    private int strength;
    private int dexterity;
    private int wisdom;
    private int stamina;
    private int luck;


    //equipment and inventory
    private List<Item> inventory;

    // Initialize random base stats and shows them to player
    public Player() {
        
        strength = (int)(Math.random()*10)+1;
        dexterity = (int)(Math.random()*10)+1;
        wisdom = (int)(Math.random()*10)+1;
        stamina = (int)(Math.random()*10)+1;
        luck = (int)(Math.random()*10)+1;

        showStats();

        this.inventory = new ArrayList<>();

    }
    //method to print out stats
    public String showStats(){
        String stats = "Strength: " + strength + " Dexterity: " + dexterity + " Wisdom " + wisdom + " Stamina: " + stamina + " Luck: " + luck;
        return stats;
    }

    public Player(String startingRoomId) {
        this.currentRoomId = startingRoomId;
        this.inventory = new ArrayList<>();
    }

    // Method to display all items in inventory
    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your inventory contains:");
            for (int i = 0; i < inventory.size(); i++) {
                Item item = inventory.get(i);
                System.out.println((i + 1) + ". " + item.getName());
                
            }
        }
    }

    // Method to show weapon and armor equiped


    // Method to equip weapon and armor

    //Method to use items


    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }
}