package general;
import java.util.Map;

import player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import rooms.Room;

public class Game {
    private static Game game;

    public static Game getGame() {
        return game;
    }

    public static void printText(String text){
        game.getGui().printText(text);
    }

    private Map<String, Room> rooms;
    private Player player;
    private Room[][] roomGrid = new Room[8][8];
    private int[] currentRoom = new int[2];
    private AdventureGUI gui;

    public Game() {
        initRooms();
        player = new Player();
        game = this;
    }

    public void setGui(AdventureGUI gui){
        this.gui = gui;
    }

    public AdventureGUI getGui(){
        return gui;
    }

    public void onPlayerTurn(){
        Game.printText("");
        this.getCurrentRoom().onPlayerTurn(player);
    }

    public void processCommand(String input) {
        if (CommandParser.parse(this, input, player, rooms, roomGrid)){
            onPlayerTurn();
        }
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
        numEachRoom.put("rangerBossRoom", 1);
        numEachRoom.put("minotaurBossRoom", 1);
        numEachRoom.put("mobRoom", 28);
        numEachRoom.put("trapRoom", 10);
        numEachRoom.put("treasureRoom", 7);
        numEachRoom.put("corridor", 15);

        List<String> roomTypes = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : numEachRoom.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                roomTypes.add(entry.getKey());
            }
        }
        Collections.shuffle(roomTypes);

        int index = 0;
        for (int row = 0; row < roomGrid.length; row++) {
            for (int col = 0; col < roomGrid[0].length; col++) {
                //get random key from numEachRoom that does not map to 0
                String randomKey = roomTypes.get(index);
                index++;

                Room room = Room.createRoom(randomKey, roomCount, row, col);
                roomGrid[row][col] = room;
                numEachRoom.put(randomKey, numEachRoom.get(randomKey) - 1);
                rooms.put(room.getId(), room);
                roomCount++;

                if (randomKey.equals("startingRoom")){
                    currentRoom = new int[]{row, col};
                    room.onPlayerEnter(player);
                }
            }
        }
    }
}