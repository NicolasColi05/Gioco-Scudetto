package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.DiceThrow;

public class Dice6ThrowImpl implements DiceThrow {

    @Override
    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

}
