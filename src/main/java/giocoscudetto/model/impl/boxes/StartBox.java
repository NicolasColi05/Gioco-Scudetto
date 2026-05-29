package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class StartBox implements Boxes{

    private static final String image = "casella_31.png";
    private final int position;
    private static final String description = "Box Event: Start. Start of the match, throw the dice[0-6]";

    public StartBox(final int position) {
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
        System.out.println("Il gioco è iniziato");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Start box";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
        return StartBox.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return StartBox.description;
    }
}
