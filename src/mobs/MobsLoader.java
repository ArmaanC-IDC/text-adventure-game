package mobs;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MobsLoader {
    private static HashMap<String, HashMap<String, Integer>> mobsConfig;
    private static final String filePath = "mobs.json";

    static {
        mobsConfig = new HashMap<String, HashMap<String, Integer>>();
        try{
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(new FileReader(filePath), JsonObject.class);

            for (HashMap.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                String mobName = entry.getKey();
                System.out.println(mobName);
                JsonObject mobStats = entry.getValue().getAsJsonObject();

                HashMap<String, Integer> statsMap = new HashMap<>();
                statsMap.put("hp", mobStats.get("hp").getAsInt());
                statsMap.put("armor", mobStats.get("armor").getAsInt());

                mobsConfig.put(mobName, statsMap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getMobConfig(String mob, String property) { return mobsConfig.get(mob).get(property); }
}
