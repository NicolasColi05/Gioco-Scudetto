package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

/**
 * Class that represents the first half box on the board.
 */
public class FirstHalfBox implements Boxes {

    private static final String IMAGE = "casella_1.png";
    private static final String DESCRIPTION = "Box Event: First Half. If you land on this box,"
                                        + " you are in the second half of the game the dice"
                                        + " that you throw is a 0-6 dice";

    private final int position;

    /**
     * Constructor for the FirstHalfBox class.
     * 
     * @param position the position of the box on the board
     */
    public FirstHalfBox(final int position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void event(final Match match) {
        System.out.println("Fine primo tempo");
        match.turn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getName() {
        return "First Half";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getImage() {
        return FirstHalfBox.IMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getDescription() {
        return FirstHalfBox.DESCRIPTION;
    }

}
