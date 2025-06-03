package general;
import java.util.Map;

import player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import rooms.Room;
import rooms.RoomsLoader;

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
    private Room[][] roomGrid;
    private int[] currentRoom = new int[2];
    private AdventureGUI gui;
    private Map<String, Integer> numEachRoom = new HashMap<String, Integer>();
    private boolean isRunning = true;
    private int gappleCounter = 0;
    private static int mobsKilled = 0;
    private static int totalCoinsCollected = 0;

    public Game() {
        player = new Player();
        game = this;
        int gridSize = RoomsLoader.getRoomConfig("gridSize");

        //add the number of each room in rooms.json to numEachRoom (used by initRooms())
        int totalRooms = 4; //starting and three boss rooms.
        numEachRoom.put("startingRoom", 1);
        numEachRoom.put("knightBossRoom", 1);
        numEachRoom.put("rangerBossRoom", 1);
        numEachRoom.put("minotaurBossRoom", 1);

        numEachRoom.put("mobRoom", RoomsLoader.getRoomConfig("numMobRooms"));
        numEachRoom.put("trapRoom", RoomsLoader.getRoomConfig("numTrapRooms"));
        numEachRoom.put("treasureRoom", RoomsLoader.getRoomConfig("numTreasureRooms"));
        numEachRoom.put("corridor", RoomsLoader.getRoomConfig("numCorridors"));

        //make sure there are the correct number of rooms, otherwise initRooms() may not work
        totalRooms += RoomsLoader.getRoomConfig("numMobRooms");
        totalRooms += RoomsLoader.getRoomConfig("numTrapRooms");
        totalRooms += RoomsLoader.getRoomConfig("numTreasureRooms");
        totalRooms += RoomsLoader.getRoomConfig("numCorridors");

        if (gridSize*gridSize != totalRooms){
            System.out.println("Must have exactly gridSize^2 rooms, including the starting room and the three boss rooms");
        }

        //init the roomGrid
        roomGrid = new Room[gridSize][gridSize];
        initRooms();
    }

    public void setGui(AdventureGUI gui){
        this.gui = gui;
    }

    public AdventureGUI getGui(){
        return gui;
    }

    //runs every valid player turn
    public void onPlayerTurn(){
        //new line
        Game.printText("");
        
        // Process status effects at the beginning of the turn
        player.processStatusEffects();
        
        // Only allow the room to process if player is not stunned
        if (!player.isStunned()) {
            this.getCurrentRoom().onPlayerTurn(player);
        } else {
            Game.printText("You are stunned and cannot act this turn!");
        }

        //if player is dead, set running to false
        if (player.getHp()<=0)
            isRunning = false;

        //handle golden apple (gapple) logic
        if(!player.getAppleStatus()){
            gappleCounter = 0;
        }else if(gappleCounter == 2){
            gappleCounter = 0;
            player.setAppleStatus(false);
        }else{
            gappleCounter++;
        }
    }

    public void setGappleCounter(){
        gappleCounter = 0;
    }

    public void processCommand(String input) {
        //if the commandparser returns true, the player's turn "counts" as a turn, and call onPlayerTurn
        if (CommandParser.parse(this, input, player, rooms, roomGrid)){
            onPlayerTurn();
            Game.printText("Health: " + player.getHp());
            Game.printText("Coins: "+ player.getCoins());
        }
    }

    public static Player getPlayer() {
        return Game.getGame().player;
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

    //roomCoords should be "x,y"
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

        //add one of each in numEachRoom to roomTypes. so if there are 2 traps and 1 treasure, it will be [trap, trap, treasure]
        //roomTypes is used to select a random room for each spot
        List<String> roomTypes = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : numEachRoom.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                roomTypes.add(entry.getKey());
            }
        }
        //shuffle rooms for randomization
        Collections.shuffle(roomTypes);

        //the index of roomTypes that is being accessed
        int index = 0;

        //loop through roomGrid
        for (int row = 0; row < roomGrid.length; row++) {
            for (int col = 0; col < roomGrid[0].length; col++) {
                //get the type at index from roomTypes
                String randomKey = roomTypes.get(index);
                index++;

                Room room = Room.createRoom(randomKey, roomCount, row, col);
                roomGrid[row][col] = room;
                rooms.put(room.getId(), room);
                roomCount++;

                //if the room is the starting room, set current room to the current row, col
                if (randomKey.equals("startingRoom")){
                    currentRoom = new int[]{row, col};
                    room.onPlayerEnter(player);
                }
            }
        }
    }

    public boolean getRunning(){
        return isRunning;
    }

    public void setRunning(boolean status){
        isRunning = status;
    }

    public static int getMobsKilled() {
        return mobsKilled;
    }

    public static void incrementMobsKilled() {
        mobsKilled++;
    }

    public static int getTotalCoinsCollected() {
        return totalCoinsCollected;
    }

    public static void incrementCoinsCollected() {
        totalCoinsCollected++;
    }

    public static void incrementCoinsCollected(int amount) {
        totalCoinsCollected += amount;
    }
}