package giocoscudetto.model;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Pawn;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.PawnImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.ClubImpl}.
 */
public class TestClub {

    private final Pawn pawn = new PawnImpl(1); 
    private final Club club1 = new ClubImpl("inter", pawn);

    /**
     * Testing the initial values of the new club's object.
     */
    @Test
    public void testInitialValues() {
        assertEquals(club1.getName(), "inter");
        assertNotNull(club1.getPawn());
        assertEquals(club1.getPoints(), 0);
        assertEquals(club1.getNetDiff(), 0);
    }

    /**
     * Testing adding points throught fictitious matches.
     */
    @Test
    public void testAddPoints() {
        club1.incrementPoints(2);
        assertEquals(club1.getPoints(), 2);
        club1.incrementPoints(0);
        assertEquals(club1.getPoints(), 2);

        //Testing an incoerent case where nothing should be done
        int actualPoints = club1.getPoints();
        club1.incrementPoints(-5);
        assertEquals(club1.getPoints(), actualPoints);
    }

    /**
     * Testing the net difference throught fictitious matches.
     */
    @Test
    public void testNetDiff() {
        club1.changeNetDiffs(5, 4);
        assertEquals(club1.getNetDiff(), 1);
        
        club1.changeNetDiffs(3, 3);
        assertEquals(club1.getNetDiff(), 1);
        
        club1.changeNetDiffs(0, 2);
        assertEquals(club1.getNetDiff(), -1);
        
        //Testing an incoerent case where nothing should be done
        int actualNetDiff = club1.getNetDiff();
        club1.changeNetDiffs(-4, 6);
        assertEquals(club1.getNetDiff(), actualNetDiff);
        
        club1.changeNetDiffs(0, -1);
        assertEquals(club1.getNetDiff(), actualNetDiff);
    }
}
