package item.passive;
import general.Game;
import item.Item;
import player.Player;
import item.ItemLoader;

public class GoldenApple extends Item{
    public GoldenApple(){
        
        super("GoldenApple",ItemLoader.getItemConfig("goldenApple", "weight")); //golden apple
    }

    public boolean useItem(String[] args){
        Game.printText("You used a golden apple");// Inform the player
        Player p = Game.getPlayer();// Get the current player
        Game g = Game.getGame();// Get the current game instance
        p.setAppleStatus(true);
        p.setHp(p.getMaxHp());
        g.setGappleCounter(); // these 3 lines are for healing purposes
        p.getInventory().remove(this); //removes from inventory
        return true;//returns true to indicate it works
    }
}
