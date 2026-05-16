package giocoscudetto.model.impl;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Pawn;
import giocoscudetto.model.api.Turn;


public class TurnImpl implements Turn {

    //Class Fields
    private Club club1;
    private Club club2;
    private Club currentPlayer;
    private MainDice dice;

    //Class Constructor
    public TurnImpl(Club club1, Club club2) {

        this.club1 = club1;
        this.club2 = club2;
        this.dice = new MainDice();
        chooseStartingPlayer(); 
    }

    @Override
    public void chooseStartingPlayer() {
        int roll1 = dice.rollDice() + dice.rollDice();
        int roll2 = dice.rollDice() + dice.rollDice();

        if (roll1 >= roll2) {
            currentPlayer= club1;
        } else {
            currentPlayer = club2;
        }
    }
    @Override
    public Club getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void switchTurn() {
        if (currentPlayer == club1) {
            currentPlayer = club2;
        } else {
            currentPlayer = club1;
        }
    }

}
