package item.passive;
import item.Item;

public class NonTakeableItem extends Item{
    public String onTakeAttempt;

    public NonTakeableItem(String name){
        super(name, 0);
        onTakeAttempt = "You can't take that!";
    }

    public NonTakeableItem(String name, String onTakeAttempt){
        super(name, 0);
        this.onTakeAttempt = onTakeAttempt;
    }

    public boolean getTakeable(){
        return false;
    }

    public String getOnTakeAttempt() { return onTakeAttempt; }
}
