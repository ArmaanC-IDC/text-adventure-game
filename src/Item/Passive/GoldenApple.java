package item.passive;
import general.Game;
import item.Item;
import player.Player;
import item.ItemLoader;

public class GoldenApple extends Item{
    public GoldenApple(){
        super("GoldenApple",ItemLoader.getItemConfig("goldenApple", "weight"));
    }

    public boolean useItem(String[] args){
        Game.printText("You used a golden apple");
        Player p = Game.getPlayer();
        Game g = Game.getGame();
        p.setAppleStatus(true);
        p.setHp(p.getMaxHp());
        g.setGappleCounter();
        p.getInventory().remove(this);
        return true;
    }
}
