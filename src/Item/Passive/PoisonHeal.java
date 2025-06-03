package item.passive;
import general.Game;
import item.Item;
import player.Player;
import item.ItemLoader;

public class PoisonHeal extends Item{
    public PoisonHeal(){
        super("PoisonHeal",ItemLoader.getItemConfig("poisonHeal", "weight"));
    }   

    public boolean useItem(){
        Player p = Game.getPlayer();
        p.poisonHeal();// calls on a method to get rid of poison status
        return true;


    }
}
