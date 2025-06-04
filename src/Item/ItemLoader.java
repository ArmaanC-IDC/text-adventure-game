package item;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ItemLoader {
    //declare instance variables
    private static HashMap<String, HashMap<String, Integer>> itemConfig;
    private static final String filePath = "items.json";

    static {
        itemConfig = new HashMap<String, HashMap<String, Integer>>();
        try{
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(new FileReader(filePath), JsonObject.class);

            //loop through the json file and put all properties into the hashmap
            // format: {
            //     item: {
            //         property: value,
            //         property2: value
            //     },
            //     item2: {
            //         property: value
            //     }
            // }
            for (HashMap.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                String itemName = entry.getKey();
                JsonObject itemStats = entry.getValue().getAsJsonObject();
                HashMap<String, Integer> statsMap = new HashMap<>();

                for (HashMap.Entry<String, JsonElement> value : itemStats.entrySet()){
                    statsMap.put(value.getKey(), itemStats.get(value.getKey()).getAsInt());
                }

                itemConfig.put(itemName, statsMap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int getItemConfig(String item, String property) { return itemConfig.get(item).get(property); }
}
