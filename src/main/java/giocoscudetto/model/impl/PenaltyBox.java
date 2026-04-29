package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public final class PenaltyBox implements Boxes {
    
    private final int position;
    private final String name;

    public PenaltyBox() {
        this.position = 12;
        this.name = "Penalty Box";
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(Match match) {
        
    }

    @Override
    public String getName() {
        return this.name;
    }

}
