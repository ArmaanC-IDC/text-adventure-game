package rooms;

import player.Player;

public class TrapRoom extends Room {
    private static final int TRAP_ROOM_CHANCE = 90;
    private static final int TRAP_ROOM_DAMAGE_MIN = 5;
    private static final int TRAP_ROOM_DAMAGE_MAX = 10;

    public TrapRoom(int roomCount, int row, int col) {
        super("trapRoom", roomCount, row, col);
        this.name = getRandomFromArray(new String[]{
            "Hall of Snares", "Trigger Chamber", "Trap-Laden Passage"
        });
        this.description = getRandomFromArray(new String[]{
            "Pressure plates and hidden wires suggest deadly traps await. Tread lightly.",
            "The air here is too quiet. Something isn't right.",
            "You spot grooves in the floor; this corridor may be rigged with mechanisms."
        });
    }

    public String onPlayerEnter(Player player) {
        this.visited = true;
        
        if (Math.random() < TRAP_ROOM_CHANCE / 100.0) {
            int damage = (int)(Math.random() * (TRAP_ROOM_DAMAGE_MAX - TRAP_ROOM_DAMAGE_MIN)) + TRAP_ROOM_DAMAGE_MIN;
            player.takeDamage(damage);
            return getRandomFromArray(new String[]{
                "A blade flies out from the walls and hits you dealing " + damage + " damage.",
                "A pressure plate clicks. Darts shoot from hidden holes, piercing you for " + damage + " damage.",
                "You feel the floor sink slightly. Flames erupt beneath your feet, burning and dealing " + damage + " damage.",
                "A stone above you shifts. A spiked log swings down, slamming into you dealing " + damage + " damage.",
            });
        } else {
            return getRandomFromArray(new String[]{
                "A blade flies out from the walls and narrowly misses.",
                "A pressure plate clicks. Darts shoot from hidden holes and barely miss.",
                "You feel the floor sink slightly. Flames erupt beneath your feet but you jump back in time.",
                "A stone above you shifts. A spiked log swings down narrowly missing you.",
            }) + " Next time you may not be so lucky.";
        }
    }
}
