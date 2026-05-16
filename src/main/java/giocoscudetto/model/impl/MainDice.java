package giocoscudetto.model.impl;

import java.util.Random;
import giocoscudetto.model.api.Dice;

public class MainDice implements Dice {

    private final Random random = new Random();
    private final static  int[] PROBABILITIES = {
        0, 0,
        1, 1, 1, 1, 1,
        2, 2, 2, 2, 2,
        3, 3, 3, 3, 3,
        4, 4, 4, 4, 4,
        5, 5, 5, 5, 5,
        6, 6, 6, 6, 6
    };

    @Override
    public int rollDice() {
        return PROBABILITIES[random.nextInt(PROBABILITIES.length)];
    }

}
