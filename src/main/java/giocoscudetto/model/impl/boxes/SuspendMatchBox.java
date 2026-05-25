package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class SuspendMatchBox implements Boxes {

    private final String image;
    private final int position;
    private final String description = "Box Event: Suspend Match. If you land on this box, you and your opponent have to restart the game with a score of 0-0.";

    public SuspendMatchBox(final int position) {
        this.position = position;
        this.image = "casella_10.png";
    }
    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(Match match) {
        match.getClubAway().getPawn().setPosition(0);
        match.getClubHome().getPawn().setPosition(0);
        match.setGoalHome(0);
        match.setGoalAway(0);
        match.turn();
    }

    @Override
    public String getName() {
        return "Suspend Match";
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
