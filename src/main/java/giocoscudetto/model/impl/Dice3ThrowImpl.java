package giocoscudetto.model.impl;

import giocoscudetto.model.api.DiceThrow;
import java.util.Random;

public class Dice3ThrowImpl implements DiceThrow {

    private final Random random = new Random();
    
    @Override
    public int rollDice() {
        return random.nextInt(4);
    }

}
