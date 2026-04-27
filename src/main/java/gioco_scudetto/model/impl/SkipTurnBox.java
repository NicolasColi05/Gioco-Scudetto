package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.Boxes;
import gioco_scudetto.model.api.Match;
import gioco_scudetto.model.api.Club;

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
}
