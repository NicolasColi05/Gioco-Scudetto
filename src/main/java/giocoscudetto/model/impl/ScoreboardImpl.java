package giocoscudetto.model.impl;

import giocoscudetto.model.api.Scoreboard;

public class ScoreboardImpl implements Scoreboard {

    private int homeScore;
    private int guestScore;

    public ScoreboardImpl() {
        this.homeScore = 0;
        this.guestScore = 0;
    }


    /**
      * {@inheritDoc}
     */
    @Override
    public int getGuestScore() {

        return guestScore;
    }


    /**
      * {@inheritDoc}
     */
    @Override
    public int getHomeScore() {
        
        return homeScore;
    }


    /**
      * {@inheritDoc}
     */
    @Override
    public void setHomeScore(final int new_score) {
        
        this.homeScore = new_score;
    }


    /**
      * {@inheritDoc}
     */
    @Override
    public void setGuestScore(final int new_score) {

        this.guestScore = new_score;
    }


    /**
      * {@inheritDoc}
     */
    @Override
    public void increaseHomeScore() {
        
        this.homeScore = homeScore + 1;
    }


    /**
      * {@inheritDoc}
     */
    @Override
    public void increaseGuestScore() {
        
        this.guestScore = guestScore + 1;
    }

    /**
      * {@inheritDoc}
     */
    public void decreaseHomeScore() {
        this.homeScore = this.homeScore - 1;
    }

    /**
      * {@inheritDoc}
     */
    public void decreaseGuestScore() {
        this.guestScore = this.guestScore - 1;
    }

    @Override
    public String toString() {
        return homeScore + " - " + guestScore;
    }
    
}
