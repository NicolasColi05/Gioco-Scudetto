package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.Boxes;
import gioco_scudetto.model.api.Match;
import gioco_scudetto.model.api.Club;



public class JoinBox implements Boxes {
    private final int position;
    public JoinBox(int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public String getName() {
        return "Join Box";
    }

    @Override
    public void event (Match match) {
        Club current;
        Club opponent;

        if (match.getMatchStatus() == 0) {

            current = match.getClubHome();
            opponent = match.getClubAway();
        } else {
            current = match.getClubAway();
            opponent = match.getClubHome();
        }

        System.out.println(current + "entered Join Box");

        int currentPosition = current.getPawn().getPosition();
        opponent.getPawn().setPosition(currentPosition);

        System.out.println(opponent + "joined current player at position" + currentPosition);
    }
}
