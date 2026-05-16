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
    private boolean penalty = false;
    private static final int HALF_BOARD = 16;

    public MatchImpl(Club clubHome, Club clubAway) {
        this.score = new ScoreboardImpl();
        this.turn = new TurnImpl(clubHome, clubAway);
        this.dice6 = new MainDice();
        turn.chooseStartingPlayer();
        this.clubHome = clubHome;
        this.clubAway = clubAway;
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
        int dice6;
        if (this.turn.getCurrentPlayer().getPawn().getPosition() < HALF_BOARD){
            dice6 = this.dice6.rollDice() + this.dice6.rollDice();
            System.out.println("due dadi" +dice6);
            return dice6;
        }
        dice6 = this.dice6.rollDice();
        System.out.println("un dado" + dice6);
        return dice6;
    }
    
    @Override
    public int freeKickDice() {
        return this.dice6.rollDice() + this.dice6.rollDice();
    }

    @Override
    public void setPenaltyMode(boolean active) {
        this.penalty  = active;
    }

    @Override
    public boolean isPenaltyMode() {
        return this.penalty;
    }

}
