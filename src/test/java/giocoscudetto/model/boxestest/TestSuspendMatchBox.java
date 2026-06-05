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
import giocoscudetto.model.impl.boxes.SuspendMatchBox;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Simple test for {@link SuspendMatchBox}.
 */
class TestSuspendMatchBox {

    private static final int BOX_POSITION = 10;

    private Club clubHome;
    private Club clubAway;
    private Match match;
    private Boxes suspendMatchBox;

    @BeforeEach
    void setUp() {
        final Pawn pawnHome = new PawnImpl(0);
        final Pawn pawnAway = new PawnImpl(0);
        pawnAway.setPosition(22);
        pawnHome.setPosition(16);

        clubHome = new ClubImpl("home", pawnHome);
        clubAway = new ClubImpl("away", pawnAway);

        match = new MatchImpl(clubHome, clubAway);
        suspendMatchBox = new SuspendMatchBox(BOX_POSITION);
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
        assertEquals("Suspend Match", suspendMatchBox.getName());
    }

    /**
     * Tests the get position.
     */
    @Test
    void testGetPosition() {
        assertEquals(BOX_POSITION, suspendMatchBox.getPosition());
    }

    /**
     * Tests the get image.
     */
    @Test
    void testGetImage() {
        assertEquals("casella_10.png", suspendMatchBox.getImage());
    }

    /**
     * Tests the box event.
     */
    @Test
    void testEventGameMode() {
        suspendMatchBox.event(match);
        assertEquals(0, clubHome.getPawn().getPosition());
        assertEquals(0, clubAway.getPawn().getPosition());
        assertEquals(0, match.getScore().getHomeScore());
        assertEquals(0, match.getScore().getGuestScore());
    }
}
