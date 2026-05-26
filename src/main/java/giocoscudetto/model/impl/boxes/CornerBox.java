package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class CornerBox implements Boxes{

    private final int position;
    private final String image;
    private final String description = "Box Event: Corner. If you land on this box, you throw two dice and if you get a 1 you score a goal";


    public CornerBox(final int position) {
        this.position = position;
        this.image = "casella_19.png";
    }
    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(Match match) {
        match.setGameMode(Match.GameMode.CORNER);
    }

    @Override
    public String getName() {
        return "Corner Box";
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
