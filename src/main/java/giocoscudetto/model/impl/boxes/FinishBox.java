package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class FinishBox implements Boxes{

    private final int position;
    private static final String image = "casella_32.png";
    private static final String description = "Box event: Last Box. If you land on this box the game will end";

    public FinishBox(final int position) {
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
        match.getClubHome().getPawn().setPosition(32);
        match.getClubAway().getPawn().setPosition(32);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Finish Box";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
        return FinishBox.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return FinishBox.description;
    }
    
}
