package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.Turn;
import gioco_scudetto.model.api.Club;
import gioco_scudetto.model.api.Pawn;
import java.util.Random;

public class TurnImpl implements Turn {

    private static final int HALF_BOARD = 16;
    //Class Fields
    private Club club1;
    private Club club2;
    private Club currentPlayer;
    private Random random;

    //Class Constructor
    public TurnImpl(Club club1, Club club2) {
        
        this.club1 = club1;
        this.club2 = club2;
        this.random = new Random();
        chooseStartingPlayer(); 
    }

    @Override
    public void chooseStartingPlayer() {
        int roll1 = rollDie() + rollDie();
        int roll2 = rollDie() + rollDie();

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

    @Override 
    public int rollDice(Club club)  {
        Pawn pawn = club.getPawn();
        int position = pawn.getPosition();

        if (position < HALF_BOARD) {
            return rollDie() + rollDie();
        } else {
            return rollDie();
        }
    }

    private int rollDie() {
        return random.nextInt(6)+1;
    }
}