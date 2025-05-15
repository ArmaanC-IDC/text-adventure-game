import java.util.Map;

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
                if (room.getExits().containsKey(dir)) {
                    game.setCurrentRoom(room.getExits().get(dir));
                    return game.getCurrentRoom().onPlayerEnter() + "\n" + game.getCurrentRoom().getLongDescription();
                } else {
                    return "You can't go that way.";
                }
            case "look":
                return room.getLongDescription();
            default:
                return "Unknown command.";
        }
    }
}