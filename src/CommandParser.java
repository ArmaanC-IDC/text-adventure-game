import java.util.Map;

import mobs.Mob;
import player.Player;
import rooms.Room;

public class CommandParser {
    public static String parse(Game game, String input, Player player, Map<String, Room> rooms, Room[][] roomGrid) {

        String[] tokens = input.trim().split(" ");
        if (tokens.length == 0) return "Enter a command.";

        String cmd = tokens[0].toLowerCase();
        Room room = game.getCurrentRoom();

        switch (cmd) {
            case "go":
                if (tokens.length < 2) return "Go where?";
                String dir = tokens[1];
                if (!room.getExits().containsKey(dir)) return "You can't go that way.";
                if (room.getBlockedExits().containsKey(dir) && room.getBlockedExits().get(dir)) return "That exit is blocked";

                game.setCurrentRoom(room.getExits().get(dir));
                return game.getCurrentRoom().onPlayerEnter(player) + "\n" + game.getCurrentRoom().getLongDescription();
            case "look":
                return room.getLongDescription();
            default:
                return "Unknown command.";
        }
    }
}