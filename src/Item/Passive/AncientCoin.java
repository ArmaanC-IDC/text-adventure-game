package item.passive;
import general.Game;
import item.Item;


public class AncientCoin extends Item{
    public AncientCoin(){
        super("AncientCoin",1);
    }

    public boolean useItem(){
        Game.printText("Can't use this item right now :(");
        return false;
    }
}

