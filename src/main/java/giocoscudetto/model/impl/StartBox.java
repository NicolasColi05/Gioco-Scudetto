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
        //potremmo fare che parte sempre la squadra di casa e se il turno è della squadra fuori casa imposto un booleano 
        //così quando arriverà la squadra per finire la partita il booleano darà il permesso per attivare la procedura di fine partita
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
