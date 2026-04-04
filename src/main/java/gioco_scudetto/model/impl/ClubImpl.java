package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.Club;
import gioco_scudetto.model.api.Pawn;

public class ClubImpl implements Club{
    
    //Class Fields
    private String name;
    private Pawn pawn;
    private int points;
    private int netDiff;

    //Class Constructor
    public ClubImpl(final Pawn pawnSelected) {
         //Setting a standard name and pawn selected
        this.pawn = pawnSelected;
        this.name = "STANDARD";
    }

    //Class Method
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public int getNetDiff() {
        return this.netDiff;
    }

    @Override
    public Pawn getPawn() {
        return this.pawn;
    }
    
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void incrementPoints(final int points) {
        this.points += points;
    }

    @Override
    public void changeNetDiffs(final int goalScored, final int goalConceded) {
        this.netDiff += (goalScored + goalConceded);
    }

    @Override
    public void changePawn(Pawn pawnSelected) {
        this.pawn = pawnSelected;
    }
}