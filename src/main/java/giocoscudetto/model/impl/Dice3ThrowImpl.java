package giocoscudetto.model.impl;

import giocoscudetto.model.api.DiceThrow;

public class Dice3ThrowImpl implements DiceThrow {

    @Override
    public int rollDice() {
        return (int) (Math.random() * 3) + 1;
    }

}
