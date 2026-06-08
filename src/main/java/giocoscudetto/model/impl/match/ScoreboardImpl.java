package giocoscudetto.model.impl.match;

import giocoscudetto.model.api.match.Scoreboard;

/**
 * This class represents the scoreboard of the match.
 */
public class ScoreboardImpl implements Scoreboard {

    private int homeScore;
    private int guestScore;

    /**
     * Constructor of the ScoreboardImpl class,
     * it initializes the home and guest score to 0.
     */
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
    public void setHomeScore(final int newScore) {
        this.homeScore = newScore;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setGuestScore(final int newScore) {
        this.guestScore = newScore;
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
    @Override
    public void decreaseHomeScore() {
        this.homeScore = this.homeScore - 1;
        if (this.homeScore < 0) {
            this.homeScore = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseGuestScore() {
        this.guestScore = this.guestScore - 1;
        if (this.guestScore < 0) {
            this.guestScore = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return homeScore + " - " + guestScore;
    }
}
