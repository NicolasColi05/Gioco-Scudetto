package giocoscudetto.model.impl.dices;

import giocoscudetto.model.api.Dice;
import java.util.Random;

public class ResultDice implements Dice {

    private final Random random = new Random();
    private final static  int[] PROBABILITIES = {
        3, 3, 3, 3, 3,
        2, 2, 2, 2, 2, 2, 2,
        1, 1, 1, 1, 1, 1, 1, 1, 1,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    };

    @Override
    public int rollDice() {
        return PROBABILITIES[random.nextInt(PROBABILITIES.length)];
    }

}
