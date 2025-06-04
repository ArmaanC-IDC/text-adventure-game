package item.passive;
import general.Game;
import item.Item;
import player.Player;
import item.ItemLoader;

public class HealPotion extends Item{
    private int strength;
    public HealPotion(){ 
        super("HealPotion", ItemLoader.getItemConfig("healPotion", "weight")); //call parent class
        this.strength = ItemLoader.getItemConfig("healPotion", "strength"); //strength of heal potion
    }

    public boolean useItem(String[] args){ //relatively same as golden apple
        Player p = Game.getPlayer();
        if(p.getHp() > p.getMaxHp() - strength){ //if you are too healthy
            Game.printText("You are too healthy to use that potion");
            return false;
        }else{
            p.setHp(p.getHp() + strength); // adds a set amount to health
            Game.printText("You used a heal potion");
            p.getInventory().remove(this); // remove it from inventory
            return true;
        } 
    }

}
