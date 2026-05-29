package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;

public class SkipTurnBox implements Boxes {

    private static final String IMAGE = "casella_7.png";
    private final int position;
    private static final String DESCRIPTION = "Box Event: Skip Turn. If you land on this box, you lose your next turn.";

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
    public void event(Match match) {
        
        Club current = match.getCurrentPlayer();
        match.setSkipTurn(current);
        match.turn();
        System.out.println(current.getName() + "skip the next turn");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
        return SkipTurnBox.IMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return SkipTurnBox.DESCRIPTION;
    }
}
