package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

/**
 * This class represents the Corner Box.
 */
public class CornerBox implements Boxes {

    private static final String DESCRIPTION = "Box Event: Corner. If you land on this box,"
                                    + " you throw two dice and if you get a 1 you score a goal";
    private final int position;
    private final String image;

    /**
     * Constructor of the CornerBox class.
     * 
     * @param position the position of the box on the board.
     */
    public CornerBox(final int position) {
        this.position = position;
        this.image = "casella_19.png";
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
        match.setGameMode(Match.GameMode.CORNER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Corner Box";
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
        return DESCRIPTION;
    }
}
