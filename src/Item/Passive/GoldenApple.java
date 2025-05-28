package item.passive;
import general.Game;
import item.Item;
import player.Player;

public class GoldenApple extends Item{
    public GoldenApple(){
        super("GoldenApple",2);
    }

    public boolean useItem(String[] args){
        Game.printText("You used a golden apple");
        Player p = Game.getPlayer();
        Game g = Game.getGame();
        p.setAppleStatus(true);
        p.setHp(p.getMaxHp());
        g.setGappleCounter();
        return true;
    }
}
