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

        this.clubHome = new ClubImpl("home", 1);
        this.clubAway = new ClubImpl("away", 2);
        this.clubHome.getPawn().setPosition(16);
        this.clubAway.getPawn().setPosition(22);

        this.match = new MatchImpl(clubHome, clubAway);
        this.suspendMatchBox = new SuspendMatchBox(BOX_POSITION);
        this.match.setGoalHome(3);
        this.match.setGoalAway(2);
        if (!this.match.getCurrentPlayer().equals(clubHome)) {
            this.match.turn();
        }
    }

    /**
     * Tests the get name.
     */
    @Test
    void testGetName() {
        assertEquals("Suspend Match", this.suspendMatchBox.getName());
    }

    /**
     * Tests the get position.
     */
    @Test
    void testGetPosition() {
        assertEquals(BOX_POSITION, this.suspendMatchBox.getPosition());
    }

    /**
     * Tests the get image.
     */
    @Test
    void testGetImage() {
        assertEquals("casella_10.png", this.suspendMatchBox.getImage());
    }

    /**
     * Tests the box event.
     */
    @Test
    void testEventGameMode() {
        suspendMatchBox.event(match);
        assertEquals(0, this.clubHome.getPawn().getPosition());
        assertEquals(0, this.clubAway.getPawn().getPosition());
        assertEquals(0, this.match.getScore().getHomeScore());
        assertEquals(0, this.match.getScore().getGuestScore());
    }
}
