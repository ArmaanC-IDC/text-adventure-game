package item.passive;
import item.Item;

public class NonTakeableItem extends Item{// A type of item that cannot be picked up
    public String onTakeAttempt;// Message to display when a player tries to take this item

    public NonTakeableItem(String name){
        super(name, 0);
        onTakeAttempt = "You can't take that!";
    }

    public NonTakeableItem(String name, String onTakeAttempt){
        super(name, 0);// Weight is 0 since it can't be carried nad still non-carryable
        this.onTakeAttempt = onTakeAttempt; // Custom message on take attempt
    }

    public boolean getTakeable(){
        return false;  // Always false: this item cannot be taken
    }

    public String getOnTakeAttempt() { return onTakeAttempt; } // Get the message when a player tries to take it
}
