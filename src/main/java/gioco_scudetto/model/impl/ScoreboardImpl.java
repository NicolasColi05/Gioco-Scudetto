package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.Scoreboard;

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
    public void setHomeScore(int n_goals) {
        
        this.homeScore = homeScore + n_goals;
    }

    @Override
    public void setGuestScore(int n_goals) {

        this.guestScore = guestScore + n_goals;
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
