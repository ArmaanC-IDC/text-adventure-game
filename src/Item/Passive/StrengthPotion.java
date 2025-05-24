package item.Passive;
import general.Game;
import player.Player;
import item.Item;

public class StrengthPotion extends Item{
    private static final int strengthBoost = 10;
    public StrengthPotion(){ 
        super("strength potion", 1);
    }

    public boolean useItem(String[] args){
        Player player = Game.getGame().getPlayer();
        player.setStrength(player.getStrength() + strengthBoost);
        return true;
    }
}
