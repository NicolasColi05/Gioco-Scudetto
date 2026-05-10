package giocoscudetto.model.impl;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Dice;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Scoreboard;

public class MatchImpl implements Match {

    private Club clubHome;
    private Club clubAway;
    private final Scoreboard score;
    private final TurnImpl turn;
    private final Dice dice6;
    private static final int HALF_BOARD = 16;

    public MatchImpl() {
        this.score = new ScoreboardImpl();
        this.turn = new TurnImpl(clubHome, clubAway);
        this.dice6 = new Dice6Impl();
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
    public final void setGoalHome(int goal){
        this.score.setHomeScore(goal);
    }

    @Override
    public final void setGoalAway(int goal){
        this.score.setGuestScore(goal);
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
            return this.dice6.rollDice() + this.dice6.rollDice();
        }
        return this.dice6.rollDice();
    }
    
    @Override
    public int freeKickDice() {
        return this.dice6.rollDice() + this.dice6.rollDice();
    }

}
