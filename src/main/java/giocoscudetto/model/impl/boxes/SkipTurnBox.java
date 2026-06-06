package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;

/**
 * Box that makes the current player skip the next turn.
 */
public final class SkipTurnBox implements Boxes {

    private static final String IMAGE = "casella_7.png";
    private static final String DESCRIPTION = "Box Event: Skip Turn. If you land on this box,"
    + "you lose your next turn.";
    private final int position;

    /**
     * Creates a SkipturnBox.
     * 
     * @param position box position
     */
    public SkipTurnBox(final int position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosition() {
        return position;
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public String getName() {
        return "Skip Turn";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void event(final Match match) {
        final Club current = match.getCurrentPlayer();
        match.setSkipTurn(current);
        match.turn();
        //System.out.println(current.getName() + " skip the next turn");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
        return IMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
