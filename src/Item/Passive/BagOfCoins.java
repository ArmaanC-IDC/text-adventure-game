package item.Passive;
import item.Item;

public class BagOfCoins extends Item{
    private int amt;
    public BagOfCoins(){
        super("Bag of Coins", 10);
        int amt = 0;
    }

    public void addCoins(int num){
        amt += num;
    }

    public int getCoins(){
        return amt;
    }
}
