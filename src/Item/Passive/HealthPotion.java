package item.Passive;
import general.Game;
import player.Player;
import item.Item;

public class HealthPotion extends Item{
    private static final int healthBoost = 10;
    public HealthPotion(){ 
        super("health potion", 1);
    }

    public boolean useItem(){
        Player player = Game.getGame().getPlayer();
        if (player.getMaxHp()-player.getHp()<healthBoost){
            Game.printText("You are to healthy to use that potion");
            return false;
        }
        player.setHp(player.getHp() + healthBoost);
        return true;
    }
}
