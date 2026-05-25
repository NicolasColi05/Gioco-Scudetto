package giocoscudetto.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Dice;
import giocoscudetto.model.api.GoalNet;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Scoreboard;
import giocoscudetto.model.impl.dices.MainDice;
import giocoscudetto.model.impl.dices.ResultDice;

public class MatchImpl implements Match {

    private static final int HALF_BOARD = 16;
    private Club clubHome;
    private Club clubAway;
    private final Scoreboard score;
    private final TurnImpl turn;
    private final Dice dice6;
    private final Dice dice3;
    private final GoalNet net = new GoalNetImpl();
    private GameMode mode = GameMode.NONE;
    private List<Integer> eventDices = new ArrayList<>();

    public MatchImpl(Club clubHome, Club clubAway) {
        this.score = new ScoreboardImpl();
        this.turn = new TurnImpl(clubHome, clubAway);
        this.dice6 = new MainDice();
        this.dice3 = new ResultDice();
        turn.chooseStartingPlayer();
        this.clubHome = clubHome;
        this.clubAway = clubAway;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Club turn() {
        turn.switchTurn();
        return turn.getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void goalHome() {
        this.score.increaseHomeScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void goalAway() {
        this.score.increaseGuestScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setGoalHome(int goal){
        this.score.setHomeScore(goal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeGoalHome() {
        this.score.decreaseHomeScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeGoalAway() {
        this.score.decreaseGuestScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setGoalAway(int goal){
        this.score.setGuestScore(goal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Club getClubHome() {
        return clubHome;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Club getClubAway() {
        return clubAway;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Scoreboard getScore() {
        return score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Club getCurrentPlayer() {
        return turn.getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int rollDice() {
        if (turn.hasToSkip(turn.getCurrentPlayer())) {
            System.out.println(turn.getCurrentPlayer().getName() + " skips the turn");
            turn.consumeSkip(turn.getCurrentPlayer());
            turn.switchTurn();
            return 0;
        }



        int dice6;
        if (this.turn.getCurrentPlayer().getPawn().getPosition() < HALF_BOARD){
            dice6 = this.dice6.rollDice() + this.dice6.rollDice();
            System.out.println("due dadi" +dice6);
            return 21;// dice6;
        }
        dice6 = this.dice6.rollDice();
        System.out.println("un dado" + dice6);
        return dice6;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int freeKickDice() {
        return this.dice6.rollDice() + this.dice6.rollDice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameMode(final GameMode mode) {
        this.mode  = mode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGameMode() {
        return this.mode.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSkipTurn(Club club) {
        turn.setSkipTurn(club);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        return this.clubHome.getName() + " - " + this.clubAway.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Club getWinnerClub(){
        if(this.score.getHomeScore() > this.score.getGuestScore()){
            return this.clubHome;
        }else if (this.score.getHomeScore() < this.score.getGuestScore()){
            return this.clubAway;
        }else{
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Club getLoserClub(){
        if(this.score.getHomeScore() < this.score.getGuestScore()){
            return this.clubHome;
        }else if (this.score.getHomeScore() > this.score.getGuestScore()){
            return this.clubAway;
        }else{
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int diceEvent() {
        int result;
        if (eventDices.size() < 1) {
            if (this.mode == GameMode.RESULT) {
                result = this.dice3.rollDice();
            } else {
                result = this.dice6.rollDice();
            }
            this.eventDices.add(result);
            return result;
        } else {
            if (this.mode == GameMode.RESULT) {
                result = this.dice3.rollDice();
            } else {
                result = this.dice6.rollDice();
            }
            this.eventDices.add(result);
            this.eventMode();
            return result;
        }
    }

    /**
     * Method that manage the event mode, it check the game mode and the value of the event 
     * dices and update the score accordingly.
     */
    @Override
    public void eventMode() {
        if (this.mode == GameMode.RESULT) {

            this.setGoalHome(this.eventDices.get(0));
            this.setGoalAway(this.eventDices.get(1));
        } else if (this.mode == GameMode.FREE_KICK) {

            if (this.eventDices.get(0) + this.eventDices.get(1) == 7) {

                if (this.getCurrentPlayer() == this.getClubAway()) {
                    this.goalAway();
                } else {
                    this.goalHome();
                }
            }
        } else if (this.mode == GameMode.CORNER) {
            if (this.eventDices.get(0) == 1|| this.eventDices.get(1) == 1) {
                if (this.getCurrentPlayer() == this.getClubAway()) {
                    this.goalAway();
                } else {
                    this.goalHome();
                }
            }
        } else if (this.mode == GameMode.PENALTY) {
            if (this.net.isGoal(new Random().nextInt(6) + 1)) {
                if (this.getCurrentPlayer().equals(this.getClubHome())) {
                    this.goalHome();
                } else {
                    this.goalAway();
                }
            }
        }
        this.eventDices.clear();
    }

    @Override
    public void setKeeperPosition(int i) {
        this.net.setGoalKeeperPosition(i);
    }

}
