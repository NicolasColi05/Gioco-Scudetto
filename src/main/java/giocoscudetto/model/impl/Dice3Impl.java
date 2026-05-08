package giocoscudetto.model.impl;

import giocoscudetto.model.api.Dice;
import java.util.Random;

public class Dice3Impl implements Dice {

    private final Random random = new Random();
    
    @Override
    public int rollDice() {
        return random.nextInt(4);
    }

}
