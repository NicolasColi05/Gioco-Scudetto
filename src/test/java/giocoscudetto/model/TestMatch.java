package giocoscudetto.model;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.impl.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestMatch {
    private Club roma = new ClubImpl("roma", null);
    private Club inter = new ClubImpl("inter", null);
    private Match match = new MatchImpl(roma, inter);

    @Test
    void TestMatchImpl() {
        assertEquals(roma, match.getClubHome());
        assertEquals(inter, match.getClubAway());
        assertEquals("roma", match.getClubHome().getName());
        assertEquals("inter", match.getClubAway().getName());
        match.goalAway();
        match.goalAway();
        match.goalAway();
        match.goalHome();
        assertEquals(3, match.getScore().getGuestScore());
        assertEquals(1, match.getScore().getHomeScore());
        match.setGoalAway(0);
        assertEquals(0, match.getScore().getGuestScore());
        System.out.println(match.getScore());
    }
    
}
