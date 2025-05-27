package item.passive;
import item.Item;

public class BagOfCoins extends Item{
    private int amt = 0;
    private static BagOfCoins bagOfCoins;
    public BagOfCoins(){
        super("Bag of Coins", 10);
        amt = 0;
    }

    public static void addCoins(int num){
        if (bagOfCoins==null){
            bagOfCoins = new BagOfCoins();
        }
        bagOfCoins.addCoins2(num);
    }

    public static int getCoins(){
        return bagOfCoins.getCoins2();
    }

    public int getCoins2(){
        return amt;
    }

    public void addCoins2(int num){

    }
}
