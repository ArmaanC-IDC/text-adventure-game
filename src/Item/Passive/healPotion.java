package item.passive;
import general.Game;
import item.Item;
import player.Player;
import item.ItemLoader;

public class HealPotion extends Item{
    public HealPotion(){ 
        super("HealPotion", ItemLoader.getItemConfig("healPotion", "weight"));
    }

    public boolean useItem(String[] args){
        Player p = Game.getPlayer();
        if(p.getHp() == p.getMaxHp()){
            Game.printText("You are too healthy to use that potion");
            return false;
        }else{
            p.setHp(p.getMaxHp());
            return true;
        } 
    }

}
