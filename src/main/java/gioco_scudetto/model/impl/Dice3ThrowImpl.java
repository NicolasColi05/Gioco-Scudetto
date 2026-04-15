package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.DiceThrow;

public class Dice3ThrowImpl implements DiceThrow {

    @Override
    public int rollDice() {
        return (int) (Math.random() * 3) + 1;
    }

}
