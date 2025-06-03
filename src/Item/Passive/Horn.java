package item.passive;
import item.Item;
import item.ItemLoader;

public class Horn extends Item{
    public Horn(){
        super("Horn", ItemLoader.getItemConfig("horn", "weight")); // data only used to summon boss
    }
}
