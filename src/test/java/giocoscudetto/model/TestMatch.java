package giocoscudetto.model;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.impl.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.Test;

public class TestMatch {
    private Club roma = new ClubImpl("roma", null);
    private Club inter = new ClubImpl("inter", null);
    private Match match = new MatchImpl(roma, inter);

    @Test
    void TestMatch() {
        assertEquals(roma, match.getClubHome());
        assertEquals(inter, match.getClubAway());
        assertEquals("roma", match.getClubHome().getName());
        assertEquals("inter", match.getClubAway().getName());
        match.goalAway();
        match.goalAway();
        match.goalAway();
        
        assertEquals(3, match.getScore().getGuestScore());
        System.out.println(match.getScore());
    }
    
}
