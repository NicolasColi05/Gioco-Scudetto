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
    void TestInitial(){
        assertEquals(roma, match.getClubHome());
        assertEquals(inter, match.getClubAway());
        assertEquals(0,match.getScore().getGuestScore());
        assertEquals(0,match.getScore().getHomeScore());
    }

    @Test
    void TestGoal() {
        match.goalAway();
        match.goalAway();
        match.goalAway();
        match.goalHome();
        assertEquals(3, match.getScore().getGuestScore());
        assertEquals(1, match.getScore().getHomeScore());
        match.setGoalAway(0);
        assertEquals(0, match.getScore().getGuestScore());
    }

    @Test
    void TestDiceLogic(){
        /*match.getClubHome().getPawn().setPosition(22);
        match.getClubAway().getPawn().setPosition(22);
        match.rollDice();*/
    }
}
