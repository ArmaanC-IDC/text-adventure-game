package item;

public class Item {
    private String name;   // The display name of the item
    private int weight;    // How much the item weighs (affects carrying capacity)

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;     // Return this item’s weight
    }

    public boolean getTakeable() {
        return true;       // By default, items can be picked up
    }

    public boolean useItem(String[] args) {
        return true;       // Default use behavior (override in subclasses)
    }

    public String getType() {
        return "";         // Subclasses can override to specify item type
    }

    public String getName() {
        return name;       // Return the item’s name
    }

    public String toString() {
        return name;       // Represent this item by its name
    }

    public Item copy() {
        return new Item(this.name, this.weight); // Create a new copy with same name and weight
    }
}