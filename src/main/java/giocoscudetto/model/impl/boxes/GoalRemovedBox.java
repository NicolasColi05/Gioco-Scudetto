package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class GoalRemovedBox implements Boxes {

    private static final String BOX_NAME = "Goal Removed";

    private static final String image = "casella_33.png";
    private final int position;
    private static final String description = "Box Event: Remove Goal. If you land on this box, the ref removes a goal from your score.";

    public GoalRemovedBox(final int position) {
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
            match.removeGoalHome();
        } else {
            match.removeGoalAway();
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
        return this.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.description;
    }

}
