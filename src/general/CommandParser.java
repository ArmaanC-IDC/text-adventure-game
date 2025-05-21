package general;
import java.util.Map;

import mobs.Mob;
import player.Player;
import rooms.Room;
import rooms.MobRoom;
import general.Game;
import item.Item;

public class CommandParser {
    public static boolean parse(Game game, String input, Player player, Map<String, Room> rooms, Room[][] roomGrid) {

        String[] tokens = input.trim().split(" ");
        if (tokens.length == 0) Game.printText("Enter a command.");

        String cmd = tokens[0].toLowerCase();
        Room room = game.getCurrentRoom();

        switch (cmd) {
            case "go":
                if (tokens.length < 2) {
                    Game.printText("Go where?");
                    return false;
                }
                String dir = tokens[1];
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
            case "take":
                String target = tokens[1];
                for (Item item : game.getCurrentRoom().getItems()) {
                    if (item.getName().equals(target)){
                        item.useItem();
                        return true;
                    }
                }
                Game.printText("There is no \"" + target + "\" in the room.");
                return false;
            case "use":
                String targetItem = tokens[1];
                for (Item item : player.getInventory()) {
                    if (item.getName().equals(targetItem)){
                        item.useItem();
                        return true;
                    }
                }
                Game.printText("You do not have a \"" + targetItem + "\".");
                return false;
            case "killall":
                if (room instanceof MobRoom){
                    MobRoom mobRoom = (MobRoom) game.getCurrentRoom();
                    for (Mob mob : mobRoom.getMobs()) {
                        mob.takeDamage(10000);
                    }

                }
            case "look":
                Game.printText(room.getLongDescription());
                return true;

            case "equip":
                if (tokens.length < 5) {
                    Game.printText("Equip what from inventory?");
                    player.showInventory();
                    return false;
                }
                String item = tokens[1];
                player.equipItem(Item item);

            case "inventory":
                player.showInventory();
            //show inventory
            case "show":
                String inventory = tokens[1];
                player.showInventory();
            
            
                    


            default:
                Game.printText("Unknown command.");
                return false;
        }
    }
}