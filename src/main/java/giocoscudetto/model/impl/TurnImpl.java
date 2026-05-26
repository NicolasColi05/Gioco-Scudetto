package giocoscudetto.model.impl;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Pawn;
import giocoscudetto.model.api.Turn;
import giocoscudetto.model.impl.dices.MainDice;


public class TurnImpl implements Turn {

    //Class Fields
    private Club club1;
    private Club club2;
    private Club currentPlayer;
    private MainDice dice;
    private boolean skipClub1;
    private boolean skipClub2;

    //Class Constructor
    public TurnImpl(Club club1, Club club2) {

        this.club1 = club1;
        this.club2 = club2;
        this.dice = new MainDice();
        chooseStartingPlayer(); 
    }

    public void setSkipTurn(Club club) {
        if (club == club1) {
            skipClub1 = true;
        } else {
            skipClub2 = true;
        }
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

    public void consumeSkip(Club club) {

        if (club == club1) {
            skipClub1 = false;
        } else {
            skipClub2 = false;
        }
    }

    public boolean hasToSkip(Club club) {

        if (club == club1) {
            return skipClub1;
        }

        return skipClub2;
    }

    @Override
    public void switchTurn() {
        if (currentPlayer == club1) {
            currentPlayer = club2;
        } else {
            currentPlayer = club1;
        }
    
        if (currentPlayer == club1 && skipClub1) {
            skipClub1 = false;

            System.out.println(club1.getName() + "--skip the turn");
            currentPlayer = club2;

        } else if (currentPlayer == club2 && skipClub2) {
            skipClub2 = false;

            System.out.println(club2.getName() + "--skip the turn");
            currentPlayer = club1;
        }
    }

}
