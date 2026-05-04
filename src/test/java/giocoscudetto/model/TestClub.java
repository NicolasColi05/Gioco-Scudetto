package giocoscudetto.model;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Pawn;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.PawnImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Simple test for {@link giocoscudetto.model.impl.ClubImpl}.
 */
public class TestClub {

    private final Pawn pawn = new PawnImpl(); 
    private final Club club1 = new ClubImpl("inter", pawn);

    /**
     * Testing the initial values of the new club's object.
     */
    @Test
    void testInitialValues() {
        assertEquals(club1.getName(), "inter");
        assertInstanceOf(pawn.getClass(), club1.getPawn());
        assertEquals(club1.getPoints(), 0);
        assertEquals(club1.getNetDiff(), 0);
    }

    /**
     * Testing adding points throught fictitious matches.
     */
    @Test
    void testAddPoints() {
        club1.incrementPoints(2);
        assertEquals(club1.getPoints(), 2);
        club1.incrementPoints(1);
        assertEquals(club1.getPoints(), 3);
    }

    /**
     * Testing the net difference throught fictitious matches.
     */
    @Test
    void testNetDiff() {
        club1.changeNetDiffs(5, 4);
        assertEquals(club1.getNetDiff(), 1);
        club1.changeNetDiffs(3, 5);
        assertEquals(club1.getNetDiff(), -1);
    }
}
