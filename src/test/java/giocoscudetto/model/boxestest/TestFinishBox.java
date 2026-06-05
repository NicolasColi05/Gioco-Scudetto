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

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Simple test for {@link FinishBox}.
 */
class TestFinishBox {
    private static final int BOX_POSITION = 32;

    private Club clubHome;
    private Club clubAway;
    private Match match;
    private Boxes finishBox;

    @BeforeEach
    void setUp() {
        final Pawn pawnHome = new PawnImpl(0);
        final Pawn pawnAway = new PawnImpl(0);
        pawnAway.setPosition(26);
        pawnHome.setPosition(32);
        clubHome = new ClubImpl("home", pawnHome);
        clubAway = new ClubImpl("away", pawnAway);
        match = new MatchImpl(clubHome, clubAway);
        finishBox = new FinishBox(BOX_POSITION);
        match.setGoalHome(3);
        match.setGoalAway(2);
        if (!match.getCurrentPlayer().equals(clubHome)) {
            match.turn();
        }
    }

    /**
     * Tests the get name.
     */
    @Test
    void testGetName() {
        assertEquals("Finish Box", finishBox.getName());
    }

    /**
     * Tests the get position.
     */
    @Test
    void testGetPosition() {
        assertEquals(BOX_POSITION, finishBox.getPosition());
    }

    /**
     * Tests the get image.
     */
    @Test
    void testGetImage() {
        assertEquals("casella_32.png", finishBox.getImage());
    }

    /**
     * Tests the box event.
     */
    @Test
    void testEventGameMode() {
        finishBox.event(match);
        assertEquals(32, clubHome.getPawn().getPosition());
        assertEquals(32, clubAway.getPawn().getPosition());
        assertEquals(3, match.getScore().getHomeScore());
        assertEquals(2, match.getScore().getGuestScore());
    }
}

