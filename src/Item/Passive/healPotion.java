package item.passive;
import general.Game;
import item.Item;
import player.Player;

public class healPotion extends Item{
    public healPotion(){ 
        super("HealPotion",3);
    }

    public boolean useItem(String[] args){
        Player p = Game.getPlayer();
        if(p.getHp() == p.getMaxHp()){
            Game.printText("You are to healthy to use that potion");
            return false;
        }else{
            p.setHp(p.getMaxHp());
            return true;
        } 
    }

}
