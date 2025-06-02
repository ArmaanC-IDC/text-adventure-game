package general;
import java.util.Map;

import mobs.Mob;
import player.Player;
import rooms.*;
import general.Game;
import item.Item;
import item.passive.*;

import java.util.ArrayList;

public class CommandParser {
    //returns true/false weather it counts as a turn. Ex: look does not count as a move. failed commands do not count as moves
    public static boolean parse(Game game, String input, Player player, Map<String, Room> rooms, Room[][] roomGrid) {

        String[] tokens = input.toLowerCase().trim().split(" ");
        if (tokens.length == 0) Game.printText("Enter a command.");

        String cmd = tokens[0].toLowerCase();
        Room room = game.getCurrentRoom();

        switch (cmd) {
            case "go": //go [direction]
                //make sure there are two words
                if (tokens.length < 2) {
                    Game.printText("Go where?");
                    return false;
                }
                String dir = tokens[1];
                return go(dir, tokens, game, player, room);
            case "n": //go north
                return go("north", tokens, game, player, room);
            case "e": //go east
                return go("east", tokens, game, player, room);
            case "s": //go south
                return go("south", tokens, game, player, room);
            case "w": // go west
                return go ("west", tokens, game, player, room);
            case "take": //take [item]
                //make sure there are two words
                if (tokens.length < 2) {
                    Game.printText("Take what?");
                    return false;
                }
                //get target item
                String target = tokens[1];
                //for every item in the room, see if it is the target.
                for (Item item : game.getCurrentRoom().getItems()) {
                    if (item.getName().equalsIgnoreCase(target)){
                        //if item is not takeable, print onTakeAttempt
                        if (!item.getTakeable()){
                            Game.printText(((NonTakeableItem)item).getOnTakeAttempt());
                            return false;
                        }
                        //check if the inventory is too full for the item
                        if (player.invWeight() + item.getWeight() > player.getMaxWeight()){
                            Game.printText("Your inventory is to full.");
                            return false;
                        }
                        //take the item
                        player.getInventory().add(item);
                        game.getCurrentRoom().getItems().remove(item);
                        Game.printText("taken");
                        return true;
                    }
                }
                //if item not found, say there is none in the room
                Game.printText("There is no \"" + target + "\" in the room.");
                return false;
            case "takeall": //takeall [item]
                //make sure there are two words
                if (tokens.length < 2) {
                    Game.printText("Take what?");
                    return false;
                }
                String targets = tokens[1];
                boolean taken = false;
                ArrayList<Item> toRemove = new ArrayList<Item>();

                //for every item, try to take it. same logic as take but for all of them
                //don't remove items in this loop to avoid errors
                for (Item item : game.getCurrentRoom().getItems()) {
                    if (item.getName().equalsIgnoreCase(targets)){
                        if (!item.getTakeable()){
                            Game.printText(((NonTakeableItem)item).getOnTakeAttempt());
                            return false;
                        }
                        if (player.invWeight() + item.getWeight() > player.getMaxWeight()){
                            Game.printText("Your inventory is to full.");
                            return false;
                        }
                        player.getInventory().add(item);
                        toRemove.add(item);
                        taken = true;
                    }
                }
                if (taken){
                    Game.printText("taken all");
                    for (Item item : toRemove) {
                        game.getCurrentRoom().getItems().remove(item);
                    }
                    return true;
                }
                Game.printText("There are no \"" + targets + "\"s in the room.");
                return false;
            case "drop":
                if (tokens.length < 2) {
                    Game.printText("Drop what?");
                    return false;
                }
                String itemToDrop = tokens[1];
                for (Item item : player.getInventory()) {
                    if (item.getName().equalsIgnoreCase(itemToDrop)){
                        player.getInventory().remove(item);
                        game.getCurrentRoom().getItems().add(item);
                        Game.printText("dropped");
                        return true;
                    }
                }
                Game.printText("You do not have a \"" + itemToDrop + "\".");
                return false;
            case "dropall": //dropall [item]
                //make sure there are two words
                if (tokens.length < 2) {
                    Game.printText("Drop what?");
                    return false;
                }
                //same logic as drop but does all of them
                String targetsToDrop = tokens[1];
                boolean dropped = false;
                ArrayList<Item> toDrop = new ArrayList<Item>();

                for (Item item : Game.getPlayer().getInventory()) {
                    if (item.getName().equalsIgnoreCase(targetsToDrop)){
                        toDrop.add(item);
                        game.getCurrentRoom().getItems().add(item);
                        dropped = true;
                    }
                }
                if (dropped){
                    Game.printText("dropped all");
                    for (Item item : toDrop) {
                        Game.getPlayer().getInventory().remove(item);
                    }
                    return true;
                }
                Game.printText("You have no \"" + targetsToDrop + "\"s.");
                return false;
            case "use": //use [item] or use [weapon] on [first word in mob] [second word in mob]. Ex: use clinic, use trident on split slime
                //make sure there are two words
                if (tokens.length < 2) {
                    Game.printText("Use what?");
                    return false;
                }
                String targetItem = tokens[1];

                //loop through all items and call useItem on the target one
                for (Item item : player.getInventory()) {
                    if (item.getName().equalsIgnoreCase(targetItem)){
                        return item.useItem(tokens);
                    }
                }
                Game.printText("You do not have a \"" + targetItem + "\".");
                return false;
            case "summon": //summon
                //if in a summon room, try to summon
                if (room instanceof SummonRoom){
                    return ((SummonRoom)room).summon();
                }else{
                    Game.printText("You are not in a summoning room");
                    return false;
                }
            case "killall": //FOR TESTING killall mobs in a room
                if (room instanceof MobRoom){
                    MobRoom mobRoom = (MobRoom) game.getCurrentRoom();
                    for (Mob mob : mobRoom.getMobs()) {
                        mob.takeDamage(10000);
                    }
                    return true;
                }
                return false;
            case "look": //display long description
                Game.printText(room.getLongDescription());
                return false;
            case "inventory": //show the inventory
                player.showInventory();
                return false;
            case "heal":
                for (Item item : room.getItems()) {
                    if (item.getName().equalsIgnoreCase("clinic")){
                        Game.printText("You heal to max health using the clinic");
                        player.setHp(player.getMaxHp());
                        return true;
                    }
                }
                Game.printText("There is no clinic in this room");
                return false;
            case "help": //show list of all commands
                Game.printText("Game Commands");
                Game.printText("________________________________________");
                Game.printText("go north, go south, go east, go west");
                Game.printText("n, s, e, w");
                Game.printText("take [item]");
                Game.printText("takeall [item]");
                Game.printText("drop [item]");
                Game.printText("use [item]");
                Game.printText("use [weapon] on [mob]. Ex: use trident on hollow-eyed ranger or split slime");
                Game.printText("summon");
                Game.printText("inventory");
                Game.printText("look");
                Game.printText("________________________________________");
                return false;
        
            default:
                Game.printText("Unknown command.");
                return false;
        }
    }

    public static boolean go(String dir, String[] tokens, Game game, Player player, Room room){
        //see if that exit exists
        if (!room.getExits().containsKey(dir)) {
            Game.printText("You can't go that way.");
            return false;
        }
        //see if the exit is blocked
        if (room.getBlockedExits().containsKey(dir) && room.getBlockedExits().get(dir)) {
            Game.printText("That exit is blocked");
            return false;
        }

        //go dir direction
        game.setCurrentRoom(room.getExits().get(dir));
        game.getCurrentRoom().onPlayerEnter(player);
        Game.printText(game.getCurrentRoom().getLongDescription());
        return true;
    }
}