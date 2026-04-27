package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.Boxes;
import gioco_scudetto.model.api.Match;
import gioco_scudetto.model.api.Club;

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

        club.getPawn().setPosition(0);
        System.out.println(club + "go back to start");
    }
}
