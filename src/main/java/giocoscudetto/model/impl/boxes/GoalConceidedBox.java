package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class GoalConceidedBox implements Boxes {

    private static final String BOX_NAME = "Goal Conceded";

    private final int position;
    private static final String IMAGE = "casella_9.png";
    private static final String DESCRIPTION = "Box Event: Goal Conceded. If you land on this box, you concede a goal.";

    public GoalConceidedBox(final int position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosition() {
       return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void event(Match match) {
        if(match.getCurrentPlayer() == match.getClubHome()) {
            match.goalAway();
        } else {
            match.goalHome();
        }
        match.turn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return BOX_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
       return GoalConceidedBox.IMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return GoalConceidedBox.DESCRIPTION;
    }
}
