package giocoscudetto.model.impl;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Pawn;

/**
 * Implementing Club interface.
 */
public class ClubImpl implements Club {

    private final String name;
    private final Pawn pawn;
    private int points;
    private int netDiff;

    /**
     * @param name the name selected for the club.
     * @param pawnSelected the pawn assigned to the club.
    */
    public ClubImpl(final String name, final Pawn pawnSelected) {
        //Setting a standard name and pawn selected
        this.pawn = pawnSelected;
        this.name = name;
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
    public void incrementPoints(final int pointsReceived) {
        if (pointsReceived >= 0) {
            this.points += pointsReceived;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeNetDiffs(final int goalScored, final int goalConceded) {
        if (goalScored >= 0 && goalConceded >= 0) {
            this.netDiff += goalScored - goalConceded;
        }
    }

}
