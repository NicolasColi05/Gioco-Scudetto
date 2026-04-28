package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;

public class BackToStartBox implements Boxes {
    private final int position;
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

        //club.getPawn().setPosition(0);
        System.out.println(club + "go back to start");
    }
}
