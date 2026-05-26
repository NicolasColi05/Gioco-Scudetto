package giocoscudetto.model.boxestest;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Scoreboard;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.boxes.CesariniBox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.boxes.CesariniBox}.
 */
public class TestCesariniBox {
    
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
    public void testBoxEvent() {

        int homeClubScore = scoreboard.getHomeScore();
        int awayClubScore = scoreboard.getGuestScore();

        //Testing CesariniBox for each team, assuming they use it
        //consecutevely on it in the match
        cesariniBox.event(match);

        assertEquals(homeClubScore + 1, scoreboard.getHomeScore());
        assertEquals(awayClubScore, scoreboard.getGuestScore());
        assertEquals(match.getClubAway(), match.getCurrentPlayer());

        cesariniBox.event(match);

        assertEquals(homeClubScore + 1, scoreboard.getHomeScore());
        assertEquals(awayClubScore + 1, scoreboard.getGuestScore());
        assertEquals(match.getClubHome(), match.getCurrentPlayer());
    }

}
