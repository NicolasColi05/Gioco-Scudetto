package giocoscudetto.model.impl;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class StartBox implements Boxes{

    private final String image ;
    private final int position;
    private final String description = "Box Event: Start. Start of the match, throw the dice[0-6]";

    public StartBox(final int position) {
        this.position = position;
        this.image = "caselle_precise/casella_32.png";
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void event(Match match) {
        System.out.println("Il gioco è iniziato");
        match.turn();
    }

    @Override
    public String getName() {
        return "Start box";
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
