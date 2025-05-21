

import general.AdventureGUI;
import general.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.setGui(new AdventureGUI(game));
    }
}