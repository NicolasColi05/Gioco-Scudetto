package giocoscudetto.model.impl;

import java.awt.Image;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Pawn;

public class BackToStartBox implements Boxes {
    private final int position;
    private Pawn pawn;
    public BackToStartBox( int position) {
        this.position = position;
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
    public Image getImage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    }
}
