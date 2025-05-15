public interface Attack {
    String getName();
    void execute(Mob attacker, Player player);
}
