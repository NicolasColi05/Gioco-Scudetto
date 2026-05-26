package giocoscudetto.model.boxestest;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Scoreboard;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.boxes.GoalConceidedBox;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.boxes.GoalConceidedBox}.
 */
public class TestGoalConceided {
 
    private final Club clubHome = new ClubImpl("home", null);
    private final Club clubAway = new ClubImpl("away", null);
    private final Match match = new MatchImpl(clubHome, clubAway);
    private final Scoreboard scoreboard = match.getScore();
    private final Boxes goalConceidedBox = new GoalConceidedBox(24);

    
    @BeforeEach
    public void setUpCurrentPlayer() {

        //Setting initially the current club the home club, and fixing match status
        //to have the homeClub as the current Club
        if(match.getCurrentPlayer() != clubHome) {
            match.turn();
        }
    }

    private void verifyResult(final int homeResult,
                            final int guestResult,
                            final Club actualCurrentClub) {
        assertEquals(homeResult, scoreboard.getHomeScore());
        assertEquals(guestResult, scoreboard.getGuestScore());
        assertEquals(actualCurrentClub, match.getCurrentPlayer());
    }

    @Test
    public void testBoxEvent() {

        scoreboard.setHomeScore(2);
        scoreboard.setGuestScore(4);

        //Testing GoalConceidedBox for each team, assuming they use it
        //consecutevely on it in the match
        verifyResult(2, 4, match.getClubHome());

        goalConceidedBox.event(match);
        verifyResult(2, 5, match.getClubAway());

        goalConceidedBox.event(match);
        verifyResult(3, 5, match.getClubHome());

    }

    @Test
    public void testBoxPosition() {
        assertEquals(24, goalConceidedBox.getPosition());
    }

    @Test
    public  void testBoxName() {
        assertEquals("Goal Conceded", goalConceidedBox.getName());
    }        
        
    @Test
    public void testBoxImage() {
        assertEquals("casella_9.png", goalConceidedBox.getImage());
    }
}

