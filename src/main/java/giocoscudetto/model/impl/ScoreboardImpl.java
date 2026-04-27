package giocoscudetto.model.impl;

import giocoscudetto.model.api.Scoreboard;

public class ScoreboardImpl implements Scoreboard {

    private int homeScore;
    private int guestScore;

    public ScoreboardImpl() {
        this.homeScore = 0;
        this.guestScore = 0;
    }

    @Override
    public int getGuestScore() {

        return guestScore;
    }

    @Override
    public int getHomeScore() {
        
        return homeScore;
    }

    @Override
    public void setHomeScore(final int new_score) {
        
        this.homeScore = new_score;
    }

    @Override
    public void setGuestScore(final int new_score) {

        this.guestScore = new_score;
    }

    @Override
    public void increaseHomeScore() {
        
        this.homeScore = homeScore + 1;
    }

    @Override
    public void increaseGuestScore() {
        
        this.guestScore = guestScore + 1;
    }

    
}
