package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public final class PenaltyBox implements Boxes {
    
    private final int position;
    private static final String name = "Penalty Box";
    private static final String image = "casella_16.png";
    private static final String description = "Box Event: Penalty. If you land on this box, The opponent must decide the position of the goalkeeper, \n" 
                                    + " once finished you kick the penalty which consists of throwing a dice [1-6] if you roll a number not selected by the opponent you score a goal.";

    public PenaltyBox(final int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(final Match match) {
        match.setGameMode(Match.GameMode.PENALTY);
    }

    @Override
    public String getName() {
        return PenaltyBox.name;
    }

    @Override
    public String getImage() {
        return PenaltyBox.image;
    }

    @Override
    public String getDescription() {
        return PenaltyBox.description;
    }

}
