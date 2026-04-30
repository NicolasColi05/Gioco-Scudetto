package giocoscudetto.model.impl;

import java.awt.Image;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;

public class SkipTurnBox implements Boxes {
    private final int position;
    public SkipTurnBox(int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override 
    public String getName() {
        return "Skip Turn";
    }

    @Override
    public void event(Match match) {
        Club club=match.turn();

        System.out.println(club + "skip the next turn");
    }

    @Override
    public Image getImage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    }
}
