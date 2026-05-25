package giocoscudetto.model.boxestest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.*;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.boxes.CesariniBox;

public class TestPenaltyBox {
    private final Club clubHome = new ClubImpl("home", null);
    private final Club clubAway = new ClubImpl("away", null);
    private final Match match = new MatchImpl(clubHome, clubAway);
    private final Scoreboard scoreboard = match.getScore();
    private final Boxes cesariniBox = new CesariniBox(0);

    
    @BeforeEach
    public void setUpCurrentPlayer() {

        //Setting initially the current club the home club, and fixing match status
        //to have the homeClub as the current Club
        if(match.getCurrentPlayer() != clubHome) {
            match.turn();
        }
    }

    @Test
    public void testBox() {

        
    }
}
