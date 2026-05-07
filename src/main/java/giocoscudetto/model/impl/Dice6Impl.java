package giocoscudetto.model.impl;

import java.util.Random;
import giocoscudetto.model.api.Dice;

public class Dice6Impl implements Dice {

    private final Random random = new Random();

    @Override
    public int rollDice() {
        return random.nextInt(7);
    }

}
