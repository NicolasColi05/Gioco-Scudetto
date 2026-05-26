package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class ResultBox implements Boxes{

    private static final String image = "casella_3.png";
    private final int position;
    private static final String description = "Box Event: Result. If you land on this box, you have to throw 2 dice and the numbers you get makes the new score";

    public ResultBox(final int position) {
        this.position = position; 
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(Match match) {
        match.setGameMode(Match.GameMode.RESULT);
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
