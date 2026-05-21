package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class FinishBox implements Boxes{

    private final int position;
    private final String image;
    private final String description = "Box event: Last Box. If you land on this box the game will end";

    public FinishBox(final int position){
        this.position = position;
        this.image = "caselle_precise/casella_32.png";
    }
    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(Match match) {
        match.getClubHome().getPawn().setPosition(32);
        match.getClubAway().getPawn().setPosition(32);
    }

    @Override
    public String getName() {
        return "Finish Box";
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
