package item.passive;
import general.Game;
import item.Item;
import player.Player;


public class AncientCoin extends Item{
    public AncientCoin(){
        super("AncientCoin");
    }

    public boolean useItem(){
        Game.printText("Can't use this item right now :(");
        return false;
    }
}

