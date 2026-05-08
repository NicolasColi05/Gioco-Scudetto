package giocoscudetto.model.impl;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Scoreboard;
import giocoscudetto.model.api.DiceThrow;

public class MatchImpl implements Match {

    private Club clubHome;
    private Club clubAway;
    private final Scoreboard score;
    private final TurnImpl turn;
    private final Dice6ThrowImpl diceThrow;
    private static final int HALF_BOARD = 16;

    public MatchImpl() {
        this.score = new ScoreboardImpl();
        this.turn = new TurnImpl(clubHome, clubAway);
        this.diceThrow = new Dice6ThrowImpl();
        turn.chooseStartingPlayer();
    }

    @Override
    public final Club turn() {
        turn.switchTurn();
        return turn.getCurrentPlayer();
    }

    @Override
    public final void goalHome() {
        this.score.increaseHomeScore();
    }

    @Override
    public final void goalAway() {
        this.score.increaseGuestScore();
    }

    @Override
    public final Club getClubHome() {
        return clubHome;
    }

    @Override
    public final Club getClubAway() {
        return clubAway;
    }

    @Override
    public final Scoreboard getScore() {
        return score;
    }

    @Override
    public Club getCurrentPlayer() {
        return turn.getCurrentPlayer();
    }

    @Override
    public int rollDice() {
        if (this.turn.getCurrentPlayer().getPawn().getPosition() < HALF_BOARD){
            return this.diceThrow.rollDice() + this.diceThrow.rollDice();
        }
        return this.diceThrow.rollDice();
    }

}
