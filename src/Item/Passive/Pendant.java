package item.passive;
import item.Item;
import item.ItemLoader;

public class Pendant extends Item{
    public Pendant(){
        super("Pendant", ItemLoader.getItemConfig("pendant", "weight"));
    }
}
