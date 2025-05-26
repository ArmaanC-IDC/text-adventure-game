package player;

import java.util.ArrayList;
import java.util.List;

import general.Game;
import item.Item;

public class Player {

    // Base stats
    private int strength;
    private int speed;
    private int hp;
    private int maxHp;
    private int luck;
    private int weight;
    private boolean isStunned;
    private boolean isPoisoned;
    private boolean isWeak;
    private int maxWeight = 50;

    // equipment and inventory
    // private Map<EquipmentSlot, Item> equippedItems;
    private List<Item> inventory;

    public Player() {

        strength = (int) (Math.random() * 30) + 1;
        speed = (int) (Math.random() * 10) + 1;
        hp = (int) (Math.random() * 20) + 81;
        maxHp = hp;
        luck = (int)(Math.random()*10)+1;
        isPoisoned = false;
        isStunned = false;
        isWeak = false;

        showStats();

        this.inventory = new ArrayList<>();

    }

    public int getStrength() {
        return strength;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setStrength(int newStrength) {
        strength = newStrength;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    // method to print out stats
    public String showStats() {
        String stats = "Strength: " + strength + " Speed: " + speed + " Health: " + hp + " Luck: "
                + luck;
        return stats;
    }

    public Player(String startingRoomId) {
        this.currentRoomId = startingRoomId;
        this.inventory = new ArrayList<>();
    }

    // Method to display all items in inventory
    public void showInventory() {
        if (inventory.isEmpty()) {
            Game.printText("Your inventory is empty.");
        } else {
            Game.printText("Your inventory contains:");
            for (int i = 0; i < inventory.size(); i++) {
                Item item = inventory.get(i);
                Game.printText((i + 1) + ". " + item.getName());

            }
        }
    }

    // Method to equip weapon and armor
    // public boolean equipItem(Item item) {
    //     String type = item.getType(); // e.g., "weapon" or "armor"
    //     if (!inventory.contains(item)) {
    //         System.out.println("You don't have that item in your inventory.");
    //         return false;
    //     }
    //     if (type.equals("weapon")) {
    //         equippedWeapon = item;
    //         System.out.println("You equipped the weapon: " + item.getName());
    //         return true;
    //     } else if (type.equals("armor")) {
    //         equippedArmor = item;
    //         System.out.println("You equipped the armor: " + item.getName());
    //         return true;
    //     } else {
    //         System.out.println("You can't equip that item.");
    //         return false;
    //     }
    // }

    // Method to show weapon and armor equiped
    public void showEquipped() {
        System.out.print("Equipped Weapon: ");
        if (equippedWeapon != null) {
            System.out.println(equippedWeapon.getName());
        } else {
            System.out.println("None");
        }

        System.out.print("Equipped Armor: ");
        if (equippedArmor != null) {
            System.out.println(equippedArmor.getName());
        } else {
            System.out.println("None");
        }
    }

    // Method to use items
    public boolean useItem(Item item) {
        if (!inventory.contains(item)) {
            System.out.println("You don't have that item in your inventory.");
            return false;
        }

        // Use the item
        item.useItem();

        // remove the item from inventory if it's consumable
        if (item.getType().equals("consumable")) { // Assumes Item has an isConsumable() method.
            inventory.remove(item);
            System.out.println("You used " + item.getName() + ".");
        } else {
            System.out.println("You used " + item.getName() + ". It is not consumed.");
        }
        return true;
    }

    //returns weight of inv currently
    public int invWeight() {
        weight = 0;
        for (int i = 0; i < inventory.size(); i++) {
                Item item = inventory.get(i);
                weight += item.getWeight();  
        }
        return weight;
    }


    //removes item from inventory
    //need to add dropping it the room your in.
    public void removeItem(Item item){
        if (!inventory.contains(item)) {
            Game.printText("You don't have that item in your inventory.");
        }
        else {
            inventory.remove(item);
            Game.printText(item.getName() + "was dropped.");
        }
    }

    public void setHp(int newHp) {
        hp = newHp;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    //when player is stunned this method is used to set stun to true
    public void isStun() {
        isStunned = true;
    }

    public void isWeaken() {
        isWeak = false;
    }

    public void isPoisoned() {
        isPoisoned = false;
    }

    public void poisonHeal(){
        isPoisoned = false;
    }

    public int getHp(){
        return hp;
    }

    public int getMaxHp(){
        return maxHp;
    }

    public void stun(){
        isStunned = true;
    }

    public void poison(){
        isPoisoned = true;
    }

    public void weaken(){
        isWeak = true;
    }
}