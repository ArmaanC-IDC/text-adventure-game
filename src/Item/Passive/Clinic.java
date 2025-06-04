package item.passive;

public class Clinic extends NonTakeableItem{
    public Clinic(){
        super("Clinic", "You can't take a clinic. Use the command \"heal\" to interact with it."); // not a takeable item just in the item class used to heal the user 
    }
}
