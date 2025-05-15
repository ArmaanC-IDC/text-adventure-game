public interface Attack {
    String getName();
    void execute(Mob target);
}

public class QuickStab implements Attack {
    public String getName() { return "Quick Stab"; }
    public void execute(Mob attacker, Mob target) {
        int damage = new Random().nextInt(3) + 4;
        target.takeDamage(damage);
    }
}