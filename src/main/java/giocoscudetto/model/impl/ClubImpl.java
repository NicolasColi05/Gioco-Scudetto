package giocoscudetto.model.impl;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Pawn;

/**
 * Implementing Club interface
 */
public class ClubImpl implements Club{
    
    //Class Fields not final cause they can ben changed.
    private String name;
    private Pawn pawn;
    private int points;
    private int netDiff;

    /**
     * Constructor to set the name and pawn of the t.
     * 
     * @param name
     * @param pawnSelected
     */
    public ClubImpl(final String name, final Pawn pawn) {
        this.name = name;
        this.pawn = pawn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPoints() {
        return this.points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNetDiff() {
        return this.netDiff;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pawn getPawn() {
        return this.pawn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        if (!("".equals(name))) {
            this.name = name;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incrementPoints(final int points) {
        if (points >= 0) {
            this.points += points;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeNetDiffs(final int goalScored, final int goalConceded) {
        if (goalScored >= 0 && goalConceded >= 0) {
            this.netDiff += (goalScored - goalConceded);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPawn(final Pawn pawnSelected) {
        if (pawn != null) {
            this.pawn = pawnSelected;
        }
    }
}