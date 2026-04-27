package giocoscudetto.model.impl;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Scoreboard;

public class MatchImpl implements Match{

    private Club clubHome;
    private Club clubAway;
    private Scoreboard score;
    private boolean turn = true;

    public MatchImpl(){
        this.score = new ScoreboardImpl();
    }

    @Override
    public Club turn() {
        if(turn){
            turn = false;
            return clubHome;
        } 
        else {
            turn = true;
            return clubAway;
        }
    }

    @Override
    public void goalHome() {
        this.score.increaseHomeScore();
    }

    @Override
    public void goalAway() {
        this.score.increaseGuestScore();
    }

    @Override
    public Club getClubHome() {
        return clubHome;
    }

    @Override
    public Club getClubAway() {
        return clubAway;
    }

    @Override
    public Scoreboard getScore() {
        return score;
    }

    
}
