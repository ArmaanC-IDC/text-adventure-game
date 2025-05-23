package general;
import java.util.Map;

import mobs.Mob;
import player.Player;
import rooms.*;
import general.Game;
import item.Item;

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
                if (!room.getExits().containsKey(dir)) {
                    Game.printText("You can't go that way.");
                    return false;
                }
                // if (room.getBlockedExits().containsKey(dir) && room.getBlockedExits().get(dir)) {
                //     Game.printText("That exit is blocked");
                //     return false;
                // }

                game.setCurrentRoom(room.getExits().get(dir));
                game.getCurrentRoom().onPlayerEnter(player);
                Game.printText(game.getCurrentRoom().getLongDescription());
                return true;
            case "n": //go north
                if (!room.getExits().containsKey("north")) {
                    Game.printText("You can't go that way.");
                    return false;
                }
                // if (room.getBlockedExits().containsKey("north") && room.getBlockedExits().get("north")) {
                //     Game.printText("That exit is blocked");
                //     return false;
                // }

                game.setCurrentRoom(room.getExits().get("north"));
                game.getCurrentRoom().onPlayerEnter(player);
                Game.printText(game.getCurrentRoom().getLongDescription());
                return true;
            case "e": //go east
                if (!room.getExits().containsKey("east")) {
                    Game.printText("You can't go that way.");
                    return false;
                }
                // if (room.getBlockedExits().containsKey("east") && room.getBlockedExits().get("east")) {
                //     Game.printText("That exit is blocked");
                //     return false;
                // }

                game.setCurrentRoom(room.getExits().get("east"));
                game.getCurrentRoom().onPlayerEnter(player);
                Game.printText(game.getCurrentRoom().getLongDescription());
                return true;

            case "s": //go south
                if (!room.getExits().containsKey("south")) {
                    Game.printText("You can't go that way.");
                    return false;
                }
                // if (room.getBlockedExits().containsKey("south") && room.getBlockedExits().get("south")) {
                //     Game.printText("That exit is blocked");
                //     return false;
                // }

                game.setCurrentRoom(room.getExits().get("south"));
                game.getCurrentRoom().onPlayerEnter(player);
                Game.printText(game.getCurrentRoom().getLongDescription());
                return true;

            case "w": // go west
                if (!room.getExits().containsKey("west")) {
                    Game.printText("You can't go that way.");
                    return false;
                }
                // if (room.getBlockedExits().containsKey("west") && room.getBlockedExits().get("west")) {
                //     Game.printText("That exit is blocked");
                //     return false;
                // }

                game.setCurrentRoom(room.getExits().get("west"));
                game.getCurrentRoom().onPlayerEnter(player);
                Game.printText(game.getCurrentRoom().getLongDescription());
                return true;

            case "take": //take [item]
                String target = tokens[1];
                for (Item item : game.getCurrentRoom().getItems()) {
                    if (item.getName().equalsIgnoreCase(target)){
                        if (!item.getTakeable()){
                            Game.printText("That item is not takeable");
                            return false;
                        }
                        player.getInventory().add(item);
                        Game.printText("taken");
                        return true;
                    }
                }
                Game.printText("There is no \"" + target + "\" in the room.");
                return false;
            case "takeall": //takeall [item]
                String targets = tokens[1];
                boolean taken = false;
                for (Item item : game.getCurrentRoom().getItems()) {
                    if (item.getName().equalsIgnoreCase(targets)){
                        if (!item.getTakeable()){
                            Game.printText("That item is not takeable");
                            return false;
                        }
                        player.getInventory().add(item);
                        taken = true;
                    }
                }
                if (taken){
                    Game.printText("taken all");
                    return true;
                }
                Game.printText("There are no \"" + targets + "\"s in the room.");
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
            
            
                    


            default:
                Game.printText("Unknown command.");
                return false;
        }
    }
}