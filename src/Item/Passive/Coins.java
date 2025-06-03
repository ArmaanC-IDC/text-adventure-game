package item.passive;
import item.Item;
import item.ItemLoader;

public class Coins extends Item{
    public Coins(){
        super("Coin", ItemLoader.getItemConfig("coin", "weight")); // data only class for coins used to summon subbosses
    }
}
