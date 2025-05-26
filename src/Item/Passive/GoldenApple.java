package item.passive;
import general.Game;
import item.Item;
import player.Player;

public class GoldenApple extends Item{
    public GoldenApple(){
        super("GoldenApple",2);
    }

    public boolean useItem(){
        Player p = Game.getPlayer();
        p.eatApple();
        return true;
    }

}
