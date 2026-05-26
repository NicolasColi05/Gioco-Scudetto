package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Pawn;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.boxes.FinishBox;

public class TestFinishBox {
    private static final int BOX_POSITION = 32;

    private Club clubHome;
    private Club clubAway;
    private Pawn pawnHome;
    private Pawn pawnAway;
    private Match match;
    private Boxes finishBox;

    @BeforeEach
    public void setUp() {
        pawnHome = new PawnImpl(0);
        pawnAway = new PawnImpl(0);
        pawnAway.setPosition(26);
        pawnHome.setPosition(32);
        clubHome = new ClubImpl("home", pawnHome);
        clubAway = new ClubImpl("away", pawnAway);
        match = new MatchImpl(clubHome, clubAway);
        finishBox = new FinishBox(BOX_POSITION);
        match.setGoalHome(3);
        match.setGoalAway(2);
        if(match.getCurrentPlayer() != clubHome) {
            match.turn();
        }
    }

    @Test
    public void testGetName(){
        assertEquals("Finish Box", finishBox.getName());
    }

    @Test
    public void testGetPosition(){
        assertEquals(BOX_POSITION, finishBox.getPosition());
    }

    @Test
    public void testGetImage(){
        assertEquals("casella_32.png", finishBox.getImage());
    }

    @Test
    public void testEventGameMode() {
        finishBox.event(match);
        assertEquals(32, clubHome.getPawn().getPosition());
        assertEquals(32, clubAway.getPawn().getPosition());
        assertEquals(3, match.getScore().getHomeScore());
        assertEquals(2, match.getScore().getGuestScore());
    }
}

