package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class FirstHalfBox implements Boxes {

    private final int position;
    private final String image;
    private final String description = "Box Event: First Half. If you land on this box,"
                                        +" you are in the second half of the game the dice"
                                        + " that you throw is a 0-6 dice";

    public FirstHalfBox(final int position) {
        this.position = position;
        this.image = "caselle_precise/casella_1.png";
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(Match match) {
        System.out.println("Fine primo tempo");
        match.turn();
    }

    @Override
    public String getName() {
        return "First Half";
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
