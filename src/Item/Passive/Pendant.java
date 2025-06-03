package item.passive;
import item.Item;
import item.ItemLoader;

public class Pendant extends Item{
    public Pendant(){
        super("Pendant", ItemLoader.getItemConfig("pendant", "weight")); // data only used to summon final boss
    }
}
