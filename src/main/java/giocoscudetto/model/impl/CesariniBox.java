package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class CesariniBox implements Boxes {

    private final int position;
    private final String image;
    private final String description = "Box Event: Cesarini zone. If you land on this box, you automatically score a goal.";

    public CesariniBox(final int position) {
        this.position = position;
        this.image = "caselle_precise/casella_30.png";
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(Match match) {
        match.turn();
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    @Override
    public String getImage() {
        return this.image;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
