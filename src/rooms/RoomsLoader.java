package rooms;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.*;

import item.Item;

public class RoomsLoader {
    private static HashMap<String, Integer> roomConfig;
    private static HashMap<String, Integer> mobRoomConfig;
    private static HashMap<String, Integer> summonRoomConfig;
    private static HashMap<String, Integer> trapRoomConfig;
    private static HashMap<String, Integer> treasureRoomConfig;

    private static String filePath = "rooms.json";
    
    //no config for corridor or starting room. both just empty rooms with nothing in them, except starting room has starting dagger
    
    static {
        try {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(new FileReader(filePath), JsonObject.class);

            //room config
            JsonObject roomConfigJson = jsonObject.getAsJsonObject("room");
            roomConfig = new HashMap<String, Integer>();
            roomConfig.put("gridSize", roomConfigJson.get("gridSize").getAsInt());
            roomConfig.put("numMobRooms", roomConfigJson.get("numMobRooms").getAsInt());
            roomConfig.put("numTrapRooms", roomConfigJson.get("numTrapRooms").getAsInt());
            roomConfig.put("numTreasureRooms", roomConfigJson.get("numTreasureRooms").getAsInt());
            roomConfig.put("numCorridors", roomConfigJson.get("numCorridors").getAsInt());
            

            //mob room config
            JsonObject mobRoomConfigJson = jsonObject.getAsJsonObject("mobRoom");
            mobRoomConfig = new HashMap<String, Integer>();
            mobRoomConfig.put("minMobs", mobRoomConfigJson.get("minMobs").getAsInt());
            mobRoomConfig.put("maxMobs", mobRoomConfigJson.get("maxMobs").getAsInt());

            //summon room config
            JsonObject summonRoomConfigJson = jsonObject.getAsJsonObject("summonRoom");
            summonRoomConfig = new HashMap<String, Integer>();
            summonRoomConfig.put("neededCoins", summonRoomConfigJson.get("neededCoins").getAsInt());

            //trap room config
            JsonObject trapRoomConfigJson = jsonObject.getAsJsonObject("trapRoom");
            trapRoomConfig = new HashMap<String, Integer>();
            trapRoomConfig.put("minDamage", trapRoomConfigJson.get("minDamage").getAsInt());
            trapRoomConfig.put("maxDamage", trapRoomConfigJson.get("maxDamage").getAsInt());
            trapRoomConfig.put("successChance", trapRoomConfigJson.get("successChance").getAsInt());

            //treasure room config
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getRoomConfig(String key) { return roomConfig.get(key); }
    public static int getMobRoomConfig(String key) { return mobRoomConfig.get(key); }
    public static int getSummonRoomConfig(String key) { return summonRoomConfig.get(key); }
    public static int getTrapRoomConfig(String key) { return trapRoomConfig.get(key); }
    public static int getTreasureRoomConfig(String key) { return treasureRoomConfig.get(key); }

}
