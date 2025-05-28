package player;

import java.io.FileReader;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PlayerLoader {
    private static HashMap<String, Integer> playerConfig = new HashMap<String, Integer>();

    private static final String filePath = "rooms.json"; 
    static {
        try {
            Gson gson = new Gson();
            JsonObject playerConfigObj = gson.fromJson(new FileReader(filePath), JsonObject.class).getAsJsonObject("player");

            //room config
            playerConfig = new HashMap<String, Integer>();
            playerConfig.put("startingHP", playerConfigObj.get("startingHP").getAsInt());
            playerConfig.put("maxInvWeight", playerConfigObj.get("maxInvWeight").getAsInt());
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getPlayerConfig(String key) { return playerConfig.get(key); }
}
