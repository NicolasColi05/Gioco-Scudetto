package giocoscudetto.model.impl;

import giocoscudetto.model.api.DiceThrow;

public class Dice6ThrowImpl implements DiceThrow {

    @Override
    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

}
