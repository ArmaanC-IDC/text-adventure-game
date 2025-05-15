import java.util.Map;

public class CommandParser {
    public static String parse(String input, Player player, Map<String, Room> rooms) {
        String[] tokens = input.trim().split(" ");
        if (tokens.length == 0) return "Enter a command.";

        String cmd = tokens[0].toLowerCase();
        Room room = rooms.get(player.getCurrentRoomId());

        switch (cmd) {
            case "go":
                if (tokens.length < 2) return "Go where?";
                String dir = tokens[1];
                if (room.getExits().containsKey(dir)) {
                    player.setCurrentRoomId(room.getExits().get(dir));
                    return rooms.get(player.getCurrentRoomId()).getLongDescription();
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