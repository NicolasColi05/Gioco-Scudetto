package gioco_scudetto;

import gioco_scudetto.controller.impl.StarterImpl;

public class GameStarter {
    public static void main(String[] args) {
        new StarterImpl().startGame();
    }
}
