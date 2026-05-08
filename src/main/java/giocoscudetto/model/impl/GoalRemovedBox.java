package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class GoalRemovedBox implements Boxes {

    private final String image;
    private final int position;
    private final String description = "Box Event: Back to Start. If you land on this box, the ref removes a goal from your score.";

    public GoalRemovedBox(final int position) {
        this.position = position;
        this.image = "caselle_precise/casella_33.png";
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(Match match) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'event'");
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
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
