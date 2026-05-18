package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class GoalRemovedBox implements Boxes {

    private static final String BOX_NAME = "Goal Removed";

    private final String image;
    private final int position;
    private final String description = "Box Event: Remove Goal. If you land on this box, the ref removes a goal from your score.";

    public GoalRemovedBox(final int position) {
        this.position = position;
        this.image = "caselle_precise/casella_33.png";
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
        //match.turn();
        if(match.getCurrentPlayer() == match.getClubHome()) {
            match.removeGoalHome();
        } else {
            match.removeGoalAway();
        }
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
