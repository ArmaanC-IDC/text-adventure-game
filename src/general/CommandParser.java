package general;
import java.util.Map;

import mobs.Mob;
import player.Player;
import rooms.*;
import general.Game;
import item.Item;
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
                go ("west", tokens, game, player, room);
            case "take": //take [item]
                String target = tokens[1];
                for (Item item : game.getCurrentRoom().getItems()) {
                    if (item.getName().equalsIgnoreCase(target)){
                        if (!item.getTakeable()){
                            Game.printText("That item is not takeable");
                            return false;
                        }
                        if (player.invWeight() + item.getWeight() > player.getMaxWeight()){
                            Game.printText("Your inventory is to full.");
                            return false;
                        }
                        player.getInventory().add(item);
                        game.getCurrentRoom().getItems().remove(item);
                        Game.printText("taken");
                        return true;
                    }
                }
                Game.printText("There is no \"" + target + "\" in the room.");
                return false;
            case "takeall": //takeall [item]
                String targets = tokens[1];
                boolean taken = false;
                ArrayList<Item> toRemove = new ArrayList<Item>();

                for (Item item : game.getCurrentRoom().getItems()) {
                    if (item.getName().equalsIgnoreCase(targets)){
                        if (!item.getTakeable()){
                            Game.printText("That item is not takeable");
                            return false;
                        }
                        if (player.invWeight() + item.getWeight() > player.getMaxWeight()){
                            Game.printText("Your inventory is to full.");
                            return false;
                        }
                        System.out.println("adding");
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
            case "use": //use [item]
                String targetItem = tokens[1];
                for (Item item : player.getInventory()) {
                    if (item.getName().equalsIgnoreCase(targetItem)){
                        return item.useItem();
                    }
                }
                Game.printText("You do not have a \"" + targetItem + "\".");
                return false;
            case "summon": //summon
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

                }
            case "look": //display long description
                Game.printText(room.getLongDescription());
                return false;
            case "inventory": //show the inventory
                player.showInventory();
                return false;
            //show inventory
            case "show": //show the inventory
                player.showInventory();
                return false;
            
            case "help": //show list of all commands
                Game.printText("Game Commands");
                Game.printText("________________________________________");
                Game.printText("go north, go south, go east, go west");
                Game.printText("n, s, e, w");
                Game.printText("take(print item here)");
                Game.printText("inventory");
                Game.printText("use(item from your inventory)");
                Game.printText("look");
                Game.printText("________________________________________");
                return false;
            default:
                Game.printText("Unknown command.");
                return false;
        }
    }

    public static boolean go(String dir, String[] tokens, Game game, Player player, Room room){
        if (!room.getExits().containsKey(dir)) {
            Game.printText("You can't go that way.");
            return false;
        }
        if (room.getBlockedExits().containsKey(dir) && room.getBlockedExits().get(dir)) {
            Game.printText("That exit is blocked");
            return false;
        }

        game.setCurrentRoom(room.getExits().get(dir));
        game.getCurrentRoom().onPlayerEnter(player);
        Game.printText(game.getCurrentRoom().getLongDescription());
        return true;
    }
}