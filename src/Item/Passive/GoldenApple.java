package item.Passive;
import general.Game;
import item.Item;
import player.Player;

public class GoldenApple extends Item{
    public GoldenApple(){
        super("GoldenApple",2);
    }

    public boolean useItem(String[] args){
        Game.printText("You used a golden apple");
        Player p = Game.getPlayer();
        p.eatGapple();
        return true;
    }
}
