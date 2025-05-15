import com.google.gson.*;

import items.Item;
import rooms.Room;

import java.io.FileReader;
import java.util.*;

public class RoomLoader {
    public Map<String, Room> loadRooms(String filePath) {
        Map<String, Room> rooms = new HashMap<>();
        try {
            Gson gson = new Gson();
            JsonObject data = gson.fromJson(new FileReader(filePath), JsonObject.class);
            for (String key : data.keySet()) {
                JsonObject obj = data.getAsJsonObject(key);
                String name = obj.get("name").getAsString();
                String desc = obj.get("description").getAsString();
                Map<String, String> exits = new HashMap<>();
                JsonObject exitsJson = obj.getAsJsonObject("exits");
                for (String dir : exitsJson.keySet()) {
                    exits.put(dir, exitsJson.get(dir).getAsString());
                }
                List<Item> items = new ArrayList<>();
                JsonArray itemArray = obj.getAsJsonArray("items");
                for (JsonElement e : itemArray) {
                    JsonObject i = e.getAsJsonObject();
                    items.add(new Item(i.get("id").getAsString(), i.get("name").getAsString(), i.get("description").getAsString()));
                }
                rooms.put(key, new Room(key, name, desc, exits, items));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }
}