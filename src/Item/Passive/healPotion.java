package item.passive;
import general.Game;
import item.Item;
import player.Player;

public class HealPotion extends Item{
    public HealPotion(){ 
        super("HealPotion",3);
    }

    public boolean useItem(String[] args){
        Player p = Game.getPlayer();
        if(p.getHp() == p.getMaxHp()){
            Game.printText("You are to healthy to use that potion");
        }else{
            p.setHp(p.getMaxHp());
            return true;
        } 
    }

}
