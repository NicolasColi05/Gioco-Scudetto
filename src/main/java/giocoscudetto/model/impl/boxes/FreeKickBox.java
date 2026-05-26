package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class FreeKickBox implements Boxes{

    private static final String image = "casella_26.png";
    private final int position;
    private static final String description = "Box Event: Free Kick. If you land on this box, you throw two dice(0-6) and if the sum is exactly 7 you score a goal";

    public FreeKickBox(final int position) {
        this.position = position;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(final Match match) {
        match.setGameMode(Match.GameMode.FREE_KICK);
    }

    @Override
    public String getName() {
        return "Freekick Box";
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
