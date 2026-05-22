package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class BackToStartBox implements Boxes {

    private final String description = "Box Event: Back to Start. If you land on this box, you must return to the starting point of the board.";
    private final int position;
    private final String image;

    public BackToStartBox(final int position) {

        this.position = position;
        this.image = "caselle_precise/casella_34.png";
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
        match.getCurrentPlayer().getPawn().setPosition(0);
        System.out.println(match.getCurrentPlayer() + "go back to start");
        match.turn();
        System.out.println("turno di "+ match.getCurrentPlayer());
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
