package giocoscudetto.model;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Pawn;
import giocoscudetto.model.impl.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMatch {
    private Club clubHome;
    private Club clubAway;
    private Pawn pawnHome;
    private Pawn pawnAway;
    private Match match;

    @BeforeEach
    void setUp() {
        pawnHome = new PawnImpl(0);
        pawnAway = new PawnImpl(0);
        clubHome= new ClubImpl("clubHome", pawnHome);
        clubAway= new ClubImpl("clubAway", pawnAway);
        match = new MatchImpl(clubHome, clubAway);
    }


    @Test
    void TestInitial(){
        assertNotNull(match.getClubHome());
        assertNotNull(match.getClubAway());
        assertEquals(clubHome, match.getClubHome());
        assertEquals(clubAway, match.getClubAway());
        assertEquals(0,match.getScore().getGuestScore());
        assertEquals(0,match.getScore().getHomeScore());
    }

    @Test
    void TestGoals() {
        match.goalHome();
        match.goalHome();
        match.goalAway();
        match.goalAway();
        match.goalAway();
        assertEquals(2, match.getScore().getHomeScore());
        assertEquals(3, match.getScore().getGuestScore());

        match.removeGoalHome();
        match.removeGoalAway();
        assertEquals(1, match.getScore().getHomeScore());
        assertEquals(2, match.getScore().getGuestScore());

        match.setGoalHome(0);
        match.setGoalAway(0);
        assertEquals(0, match.getScore().getHomeScore());
        assertEquals(0, match.getScore().getGuestScore());
    }

    @Test
    void testWinnerAndLoser() {
        assertNull(match.getWinnerClub());
        assertNull(match.getLoserClub());

        match.setGoalHome(1);
        match.setGoalAway(0);
        assertEquals(clubHome, match.getWinnerClub());
        assertEquals(clubAway, match.getLoserClub());

        match.setGoalHome(0);
        match.setGoalAway(1);
        assertEquals(clubAway, match.getWinnerClub());
        assertEquals(clubHome, match.getLoserClub());
    }

    @Test
    void TestDiceLogic(){
        match.getClubHome().getPawn().setPosition(0);
        if (match.getCurrentPlayer() != match.getClubHome()){
            match.turn();
        }
        assertTrue(match.rollDice()<=12);
        match.getClubHome().getPawn().setPosition(24);
        assertTrue(match.rollDice()<=6);
    }
}
