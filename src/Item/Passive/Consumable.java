package item.Passive;

import item.Item;

class Consumable extends Item {
    public String effect;

    public Consumable(String name, String effect) {
        super(name);
        this.effect = effect;
    }

    public String getType() {
        return "Consumable";
    }

    public boolean useItem() {
        return true;
    }

    public Item copy() {
        return new Consumable(this.name, this.effect);
    }
}
