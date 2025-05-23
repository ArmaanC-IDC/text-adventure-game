package item;

public class Item {
    private String name;
    private boolean takeable;
    private int weight;

    public Item(String name, int weight) {
        this.name = name;
        this.weight = weight;
        this.takeable = true;
    }

    public Item(String name, int weight, boolean takeable){
        this.name = name;
        this.weight = weight;
        this.takeable = takeable;
    }

    public int getWeight(){
        return weight;
    }

    public boolean getTakeable(){
        return takeable;
    }

    public boolean useItem(){
            return true;
    }

    public String getType(){
        return "";
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name;
    }

    public Item copy() {
        return new Item(this.name);
    }
}