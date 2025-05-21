package mobs;

import attack.HornCharge;
import attack.GroundSmash;
import attack.Roar;
import general.Game;

import java.util.ArrayList;

public class IronhornMinotaur extends Mob {
    public IronhornMinotaur(String id) {
        super("Ironhorn Minotaur", 120, 30, 3, new ArrayList<>());
        attacks.add(new HornCharge());
        attacks.add(new GroundSmash());
        attacks.add(new Roar());
    }
}