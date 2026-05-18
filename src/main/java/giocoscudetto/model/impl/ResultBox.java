package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.impl.MatchImpl.GameMode;

public class ResultBox implements Boxes{

    private final String image;
    private final int position;
    private final String description = "Box Event: Result. If you land on this box, you have to throw 2 dice and the numbers you get makes the new score";

    public ResultBox(final int position) {
        this.position = position;
        this.image = "caselle_precise/casella_3.png";  
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(Match match) {
        match.setGameMode(GameMode.RESULT);
        match.turn();
    }

    @Override
    public String getName() {
        return "result box";
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
