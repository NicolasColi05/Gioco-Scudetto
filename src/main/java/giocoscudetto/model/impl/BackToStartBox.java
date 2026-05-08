package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Pawn;

public class BackToStartBox implements Boxes {

    private final String description = "Box Event: Back to Start. If you land on this box, you must return to the starting point of the board.";
    private final int position;
    private Pawn pawn;
    private final String image;

    public BackToStartBox(final int position) {

        this.position = position;
        this.image = "caselle_precise/casella_2.png";
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String getName() {
        return "Back to Start";
    }

    @Override
    public void event(Match match) {
        Club club= match.turn();

        pawn.setPosition( 0);
        System.out.println(club + "go back to start");
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
