package player;

import java.util.ArrayList;
import java.util.List;

import general.Game;
import item.Item;
import item.passive.Armour;
import item.passive.Cloak;

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
    private boolean isApple;
    private int maxWeight;
    private int stunDuration;
    private int poisonDuration;
    private int weakenDuration;

    // equipment and inventory
    // private Map<EquipmentSlot, Item> equippedItems;
    private ArrayList<Item> inventory;

    public Player() {

        strength = (int) (Math.random() * 30) + 1;
        speed = (int) (Math.random() * 10) + 1;
        hp = PlayerLoader.getPlayerConfig("startingHP");
        maxHp = hp;
        luck = (int)(Math.random()*10)+1;
        isPoisoned = false;
        isStunned = false;
        isWeak = false;
        isApple = false;
        maxWeight = PlayerLoader.getPlayerConfig("maxInvWeight");

        stunDuration = 0;
        poisonDuration = 0;
        weakenDuration = 0;

        showStats();

        this.inventory = new ArrayList<>();

    }

    public int getStrength() {
        int currentStrength = strength;
        if(isApple){
            currentStrength += 10;
        }
        if(isWeak){
            currentStrength = (int)(currentStrength*0.9);
        }
        return currentStrength;
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
        String stats = "Strength: " + strength + " Speed: " + speed + " Health: " + hp + " Luck: " + luck;
        return stats;
    }

    // public Player(String startingRoomId) {
    //     this.currentRoomId = startingRoomId;
    //     this.inventory = new ArrayList<>();
    // }

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
    // public void showEquipped() {
    //     System.out.print("Equipped Weapon: ");
    //     if (equippedWeapon != null) {
    //         System.out.println(equippedWeapon.getName());
    //     } else {
    //         System.out.println("None");
    //     }

    //     System.out.print("Equipped Armor: ");
    //     if (equippedArmor != null) {
    //         System.out.println(equippedArmor.getName());
    //     } else {
    //         System.out.println("None");
    //     }
    // }

    // Method to use items
    public boolean useItem(Item item) {
        if (!inventory.contains(item)) {
            System.out.println("You don't have that item in your inventory.");
            return false;
        }

        // Use the item
        System.out.println("FIXME: player.java line 132");

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

    public int getCoins(){
        //iterates through inventory arraylist, counts and returns number of coin objects
        int coinCount = 0;
        for (Item item : inventory) {
            if (item instanceof item.passive.Coins) {
                coinCount++;
            }
        }
        return coinCount;
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



    public int findArmourResistance(){
        for(int i = 0; i < inventory.size();i++){
            Armour armour = (Armour) inventory.get(i);
            if((inventory.get(i).getType()).equals("armour")){
                return armour.getResistance();
            }
        }
        return 0;
    }

    public Item findCloak(){
        for(int i = 0; i <inventory.size();i++){
            if((inventory.get(i).getType()).equals("cloak")){
                return inventory.get(i);
            }
        }
        return null;
    }

    public void takeDamage(int damage) {
        Cloak cloak = (Cloak) findCloak();
        if(findCloak()!=null&&cloak.getProbDodge()){
            hp-= 0;
        }else{
            int damageTaken = damage + findArmourResistance();
            hp -= damageTaken;
        }  
    }

    //when player is stunned this method is used to set stun to true
    public boolean isStunned() {
        return isStunned;
    }

    public boolean isWeakened() {
        return isWeak;
    }

    public boolean isPoisoned() {
        return isPoisoned;
    }

        // Method to process status effects each turn - call this from Game.onPlayerTurn()
    public void processStatusEffects() {
        // Process poison
        if (isPoisoned && poisonDuration > 0) {
            takeDamage(2);
            Game.printText("You take 2 damage from poison! (" + poisonDuration + " turns remaining)");
            poisonDuration--;
            if (poisonDuration <= 0) {
                isPoisoned = false;
                Game.printText("The poison has worn off.");
            }
        }

        // Process stun
        if (isStunned && stunDuration > 0) {
            stunDuration--;
            if (stunDuration <= 0) {
                isStunned = false;
                Game.printText("You are no longer stunned.");
            }
        }

        // Process weaken
        if (isWeak && weakenDuration > 0) {
            weakenDuration--;
            if (weakenDuration <= 0) {
                isWeak = false;
                Game.printText("You are no longer weakened.");
            }
        }
    }

    // Healing methods
    public void poisonHeal(){
        isPoisoned = false;
        poisonDuration = 0;
        Game.printText("You have been cured of poison!");
    }

    public void cureStun() {
        isStunned = false;
        stunDuration = 0;
        Game.printText("You are no longer stunned!");
    }

    public void cureWeaken() {
        isWeak = false;
        weakenDuration = 0;
        Game.printText("You are no longer weakened!");
    }

    public void stun(){
        isStunned = true;
        stunDuration = 1;
        Game.printText("You are stunned and cannot attack!");
    }

    public void poison(){
        isPoisoned = true;
        poisonDuration = 3;
        Game.printText("You have been poisoned!");
    }

    public void weaken(){
        isWeak = true;
        weakenDuration = 2; // Weaken lasts 2 turns
        Game.printText("You have been weakened! Your damage is reduced by 10%!");
    }

    public void setAppleStatus(boolean n){
        isApple = n;
    }
    
    public boolean getAppleStatus(){
        return isApple;
    }

    public int getStunDuration() {
        return stunDuration;
    }

    public int getPoisonDuration() {
        return poisonDuration;
    }

    public int getWeakenDuration() {
        return weakenDuration;
    }

    public int getHp(){
        return hp;
    }

    public int getMaxHp(){
        return maxHp;
    }
}