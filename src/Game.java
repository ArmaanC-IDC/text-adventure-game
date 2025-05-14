import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Game {
    private Map<String, Room> rooms;
    private Player player;
    private CommandParser commandParser;

    public Game() {
        // RoomLoader roomLoader = new RoomLoader();
        // rooms = roomLoader.loadRooms("rooms.json");
        Room startingRoom = new Room("startingRoom", "Starting Room", "The room you start in");
        rooms = new HashMap<String, Room>();
        rooms.put("startingRoom", startingRoom);

        player = new Player("entrance");
        player.setCurrentRoomId("startingRoom");
        commandParser = new CommandParser();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Text Adventure Game!");
        Room currentRoom = rooms.get("startingRoom");
        System.out.println(currentRoom.getLongDescription());

        while (true) {
            
            System.out.println();
            System.out.print("> ");
            String input = scanner.nextLine();
            commandParser.parse(input, player, rooms);
        }
    }
}
