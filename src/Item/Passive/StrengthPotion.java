package item.passive;
import general.Game;
import player.Player;
import item.Item;
import item.ItemLoader;

public class StrengthPotion extends Item{
    private static final int strengthBoost = 10;
    public StrengthPotion(){ 
        super("StrengthPotion", ItemLoader.getItemConfig("strengthPotion", "weight"));
    }

    public boolean useItem(String[] args){
        Player player = Game.getPlayer();
        player.setStrength(player.getStrength() + strengthBoost); // adds a set amount to the players strength
        return true;
    }
}
