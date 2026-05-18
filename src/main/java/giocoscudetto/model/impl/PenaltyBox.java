package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public final class PenaltyBox implements Boxes {
    
    private final int position;
    private final String name;
    private final String image;
    private final String description = "Box Event: Penalty. If you land on this box, The opponent must decide the position of the goalkeeper,"
                                    + " once finished you kick the penalty which consists of throwing a dice [1-6] if you roll a number not selected by the opponent you score a goal.";

    public PenaltyBox(final int position) {
        this.position = position;
        this.name = "Penalty Box";
        this.image = "caselle_precise/casella_16.png";
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(final Match match) {
        match.setGameMode(Match.GameMode.PENALTY);;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getImage() {
        return this.image;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}
