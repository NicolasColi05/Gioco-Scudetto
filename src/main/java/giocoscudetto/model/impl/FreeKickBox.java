package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.impl.MatchImpl.GameMode;

public class FreeKickBox implements Boxes{

    private final String image;
    private final int position;
    private final String description = "Box Event: Free Kick. If you land on this box, you throw two dice(0-6) and if the sum is exactly 7 you score a goal";

    public FreeKickBox(final int position) {
        this.position = position;
        this.image = "caselle_precise/casella_26.png";
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(final Match match) {
        match.setGameMode(GameMode.FREE_KICK);
        match.turn();
    }

    @Override
    public String getName() {
        return "freekick box";
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
