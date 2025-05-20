package general;
import java.util.Map;

import mobs.Mob;
import player.Player;
import rooms.Room;
import general.Game;

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
            case "look":
                Game.printText(room.getLongDescription());
                return true;
            default:
                Game.printText("Unknown command.");
                return false;
        }
    }
}