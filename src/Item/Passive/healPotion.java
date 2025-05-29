package item.passive;
import general.Game;
import item.Item;
import player.Player;
import item.ItemLoader;

public class HealPotion extends Item{
    private int strength;
    public HealPotion(){ 
        super("HealPotion", ItemLoader.getItemConfig("healPotion", "weight"));
        this.strength = ItemLoader.getItemConfig("healPotion", "strength");
    }

    public boolean useItem(String[] args){
        Player p = Game.getPlayer();
        if(p.getHp() > p.getMaxHp() - strength){
            Game.printText("You are too healthy to use that potion");
            return false;
        }else{
            p.setHp(p.getHp() + strength);
            Game.printText("You used a heal potion");
            p.getInventory().remove(this);
            return true;
        } 
    }

}
