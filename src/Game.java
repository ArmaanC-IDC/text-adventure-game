import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import rooms.Room;

public class Game {
    private Map<String, Room> rooms;
    private Player player;
    private Room[][] roomGrid = new Room[5][5];
    private int[] currentRoom = new int[2];

    public Game() {
        // RoomLoader loader = new RoomLoader();
        // rooms = loader.loadRooms("rooms.json");
        initRooms();
        player = new Player("startingRoom");
    }

    public String processCommand(String input) {
        return CommandParser.parse(this, input, player, rooms, roomGrid);
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return roomGrid[currentRoom[0]][currentRoom[1]];
    }

    public int[] getPlayerCoords(){
        return currentRoom;
    }

    public Room[][] getRoomGrid(){
        return roomGrid;
    }

    public void setCurrentRoom(String roomCoords) {
        String[] strArray = roomCoords.split(",");
        int[] intArray = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        this.currentRoom = intArray;
    }

    private void initRooms(){
        int roomCount = 0;
        rooms = new HashMap<String, Room>();
        Map<String, Integer> numEachRoom = new HashMap<String, Integer>();
        numEachRoom.put("startingRoom", 1);
        numEachRoom.put("knightBossRoom", 1);
        numEachRoom.put("skeletonBossRoom", 1);
        numEachRoom.put("minotaurBossRoom", 1);
        numEachRoom.put("mobRoom", 8);
        numEachRoom.put("trapRoom", 4);
        numEachRoom.put("treasureRoom", 3);
        numEachRoom.put("corridor", 6);     

        for (int row = 0; row < roomGrid.length; row++) {
            for (int col = 0; col < roomGrid[0].length; col++) {
                //get random key from numEachRoom that does not map to 0
                String randomKey;
                do {
                    List<String> keys = new ArrayList<String>(numEachRoom.keySet());
                    randomKey = keys.get((int) (Math.random() * keys.size()));
                } while (numEachRoom.get(randomKey)==0);

                Room room = new Room(randomKey, roomCount, row, col);
                roomGrid[row][col] = room;
                numEachRoom.put(randomKey, numEachRoom.get(randomKey) - 1);
                rooms.put(room.getId(), room);
                roomCount++;

                if (randomKey.equals("startingRoom")){
                    currentRoom = new int[]{row, col};
                }
            }
        }
    }
}