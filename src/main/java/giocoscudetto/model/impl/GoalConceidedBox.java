package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class GoalConceidedBox implements Boxes {

    private final int position;
    private final String image;
    private final String description = "Box Event: Goal Conceded. If you land on this box, you concede a goal.";

    public GoalConceidedBox(final int position) {
        this.position = position;
        this.image = "caselle_precise/casella_9.png";

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
