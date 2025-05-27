package item.Passive;
import general.Game;
import item.Item;
import player.Player;

public class PoisonHeal extends Item{
    public PoisonHeal(){
        super("PoisonHeal",3);
    }   

    public boolean useItem(){
        Player p = Game.getPlayer();
        p.poisonHeal();
        return true;


    }
}
