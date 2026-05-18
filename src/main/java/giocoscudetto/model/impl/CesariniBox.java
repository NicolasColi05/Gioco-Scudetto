package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class CesariniBox implements Boxes {

    private static final String BOX_NAME = "Cesarini Zone";

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
        if(match.getCurrentPlayer() == match.getClubHome()) {
            match.goalHome();
        } else {
            match.goalAway();
        }
        match.turn();
    }

    @Override
    public String getName() {
        return BOX_NAME;
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
