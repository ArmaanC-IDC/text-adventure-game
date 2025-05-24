package rooms;

import item.*;

public class TreasureRoom extends Room{
    public TreasureRoom(int roomCount, int row, int col) {
        super("treasureRoom", roomCount, row, col);
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

        int maxItems = RoomsLoader.getTreasureRoomConfig("maxItems");
        int minItems = RoomsLoader.getTreasureRoomConfig("minItems");
        int numItems = (int)(Math.random() * (maxItems - minItems)) + minItems;
        for (int i = 0; i < numItems; i++) {
            Item item = ItemPool.getRandomItem();
            this.items.add(item);
            System.out.println(item.getClass().getName());
        }
        ItemPool.getRandomItem();
    }

    public void onPlayerEnter(){
        this.visited = true;
        // System.out.println("IMPLEMENT TREASURE ROOM");
    }
}
