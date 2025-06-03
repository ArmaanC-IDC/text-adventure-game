package rooms;

import item.*;

public class TreasureRoom extends Room{
    public TreasureRoom(int roomCount, int row, int col) {
        super("treasureRoom", roomCount, row, col);

        //random name and description
        this.name = getRandomFromArray(new String[]{
            "Hidden ", "Treasure ", "Glittering "
        }) + getRandomFromArray(new String[]{
            "Vault", "Cache", "Chamber"
        });
        this.description = getRandomFromArray(new String[]{
            "A chest gleams invitingly, bathed in a golden light. Could it be genuine treasure?",
            "Treasure sparkles ahead, but nothing in this place is free.",
            "An ornate box sits atop a pedestal. Riches or ruin may lie within."
        });

        //get the number of items
        int maxItems = RoomsLoader.getTreasureRoomConfig("maxItems");
        int minItems = RoomsLoader.getTreasureRoomConfig("minItems");
        int numItems = (int)(Math.random() * (maxItems - minItems)) + minItems;

        //spawn random items
        for (int i = 0; i < numItems; i++) {
            this.items.add(ItemPool.getRandomTreasureRoomItem());
        }
    }
}
