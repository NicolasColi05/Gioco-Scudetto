package giocoscudetto.model.impl;

import java.util.ArrayList;
import java.util.List;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Dice;
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
    public void removeGoalHome() {
        this.score.decreaseHomeScore();
    }

    @Override
    public void removeGoalAway() {
        this.score.decreaseGuestScore();
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
    
    @Override
    public int freeKickDice() {
        return this.dice6.rollDice() + this.dice6.rollDice();
    }

    @Override
    public void setGameMode(final GameMode mode) {
        this.mode  = mode;
    }

    @Override
    public String getGameMode() {
        return this.mode.toString();
    }

    @Override
    public void setSkipTurn(Club club) {
        turn.setSkipTurn(club);
    }

    @Override
    public String toString(){
        return this.clubHome.getName() + " - " + this.clubAway.getName();
    }

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

    private void eventMode() {
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
        }
        this.eventDices.clear();
    }

}
