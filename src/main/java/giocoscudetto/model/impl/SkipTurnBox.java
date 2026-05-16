package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;

public class SkipTurnBox implements Boxes {

    private final String image;
    private final int position;
    private final String description = "Box Event: Skip Turn. If you land on this box, you lose your next turn.";

    public SkipTurnBox(final int position) {
        this.position = position;
        this.image = "caselle_precise/casella_7.png";
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override 
    public String getName() {
        return "Skip Turn";
    }

    @Override
    public void event(Match match) {
        
        Club current = match.getCurrentPlayer();
        match.setSkipTurn(current);
        System.out.println(current + "skip the next turn");
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
