package item;

public class Item {
    public String name;

    public Item(String name) {
        this.name = name;
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