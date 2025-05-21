package player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.lang.Math.*;
import java.util.Random;

import item.Item;

public class Player {
    // Position
    private String currentRoomId;
    
    // Base stats
    private int strength;
    private int speed;
    private int wisdom;
    private int hp;
    private int maxHp;
    private int luck;
    private boolean isStunned;
    private boolean isPoisoned;
    private boolean isWeak;


    //equipment and inventory
    // private Map<EquipmentSlot, Item> equippedItems;
    private List<Item> inventory;

    public Player() {
        // Initialize random base stats and shows them to player
        strength = (int)(Math.random()*10)+1;
        speed = (int)(Math.random()*5)+1;
        wisdom = (int)(Math.random()*10)+1;
        hp = (int)(Math.random()*10)+1;
        maxHp = hp;
        luck = (int)(Math.random()*10)+1;
        isPoisoned = false;
        isStunned = false;
        isWeak = false;

        showStats();

    }

    public String showStats(){
        String stats = "Strength: " + strength + " Speed: " + speed + " Wisdom " + wisdom + " Health: " + hp + " Luck: " + luck;
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

    public String getCurrentRoomId() {
        return currentRoomId;
    }

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

    public void takeDamage(int damage){
        hp -= damage;
    }

    public void stun(){
        isStunned = true;
    }

    public void weaken(){
        isWeak = true;
    }

    public void poison(){
        isPoisoned = true;
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

    public void setHp(int newHp){
        hp = newHp;
    }
}