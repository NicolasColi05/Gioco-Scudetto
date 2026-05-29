package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class SuspendMatchBox implements Boxes {

    private static final String image = "casella_10.png";
    private final int position;
    private static final String description = "Box Event: Suspend Match. If you land on this box, you and your opponent have to restart the game with a score of 0-0.";

    /**
     * Constructor of the SuspendMatchBox class.
     * 
     * @param position the position of the box on the board.
     */
    public SuspendMatchBox(final int position) {
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
    public void event(final Match match) {
        match.getClubAway().getPawn().setPosition(0);
        match.getClubHome().getPawn().setPosition(0);
        match.setGoalHome(0);
        match.setGoalAway(0);
        match.turn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Suspend Match";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
       return SuspendMatchBox.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return SuspendMatchBox.description;
    }
}
