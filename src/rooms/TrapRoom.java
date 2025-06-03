package rooms;

import player.Player;
import general.Game;

public class TrapRoom extends Room {
    private final int TRAP_ROOM_CHANCE;
    private final int TRAP_ROOM_DAMAGE_MIN;
    private final int TRAP_ROOM_DAMAGE_MAX;

    public TrapRoom(int roomCount, int row, int col) {
        super("trapRoom", roomCount, row, col);

        //set name to random of the three
        this.name = getRandomFromArray(new String[]{
            "Hall of Snares", "Trigger Chamber", "Trap-Laden Passage"
        });

        //randomize description
        this.description = getRandomFromArray(new String[]{
            "Pressure plates and hidden wires suggest deadly traps await. Tread lightly.",
            "The air here is too quiet. Something isn't right.",
            "You spot grooves in the floor; this corridor may be rigged with mechanisms."
        });

        //traproomchance is the chance the trap room has of dealing damage. max/min damage is the amount of damage the room can do
        TRAP_ROOM_CHANCE = RoomsLoader.getTrapRoomConfig("successChance");
        TRAP_ROOM_DAMAGE_MAX = RoomsLoader.getTrapRoomConfig("maxDamage");
        TRAP_ROOM_DAMAGE_MIN = RoomsLoader.getTrapRoomConfig("minDamage");
    }

    //when the player enters, maybe damage players
    public void onPlayerEnter(Player player) {
        this.visited = true;
        
        //if goes into if statement, damage the player
        if (Math.random() < TRAP_ROOM_CHANCE/100.0) {
            //calculate damage
            int damage = (int)(Math.random() * (TRAP_ROOM_DAMAGE_MAX - TRAP_ROOM_DAMAGE_MIN)) + TRAP_ROOM_DAMAGE_MIN;
            player.takeDamage(damage);

            //random damage message
            Game.printText(getRandomFromArray(new String[]{
                "A blade flies out from the walls and hits you dealing " + damage + " damage.",
                "A pressure plate clicks. Darts shoot from hidden holes, piercing you for " + damage + " damage.",
                "You feel the floor sink slightly. Flames erupt beneath your feet, burning and dealing " + damage + " damage.",
                "A stone above you shifts. A spiked log swings down, slamming into you dealing " + damage + " damage.",
            }));
        } else { //trap misses player
            Game.printText(getRandomFromArray(new String[]{
                "A blade flies out from the walls and narrowly misses.",
                "A pressure plate clicks. Darts shoot from hidden holes and barely miss.",
                "You feel the floor sink slightly. Flames erupt beneath your feet but you jump back in time.",
                "A stone above you shifts. A spiked log swings down narrowly missing you.",
            }) + " Next time you may not be so lucky.");
        }
    }
}
