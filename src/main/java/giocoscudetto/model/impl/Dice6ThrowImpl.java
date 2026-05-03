package giocoscudetto.model.impl;

import java.util.Random;
import giocoscudetto.model.api.DiceThrow;

public class Dice6ThrowImpl implements DiceThrow {

    private final Random random = new Random();

    @Override
    public int rollDice() {
        return random.nextInt(7);
    }

}
