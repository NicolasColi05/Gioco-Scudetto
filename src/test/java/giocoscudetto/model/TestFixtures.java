package giocoscudetto.model;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.ArrayList;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Simple test for {@link giocoscudetto.model.impl.FixturesImpl}.
 */
public class TestFixtures {
    final Club roma = new ClubImpl("Club1", new PawnImpl());
    final Club inter = new ClubImpl("Club2", new PawnImpl());
    final Club napoli = new ClubImpl("Club3", new PawnImpl());
    final Club juventus = new ClubImpl("Club4", new PawnImpl());
    final ArrayList<Club> listOfClubs = new ArrayList<>();
    private final static String ROMA = "roma";
    private final static String INTER = "inter";
    private final static String NAPOLI = "napoli";
    private final static String JUVENTUS = "juventus";

    @Test
    void TestFixturesGeneration() {
        int intercount = 0;
        int romacount = 0;
        int napolicount = 0;
        int juventuscount = 0;
        roma.setName(ROMA);
        inter.setName(INTER);
        napoli.setName(NAPOLI);
        juventus.setName(JUVENTUS);
        listOfClubs.add(roma);
        listOfClubs.add(inter);
        listOfClubs.add(napoli);
        listOfClubs.add(juventus);
        final FixturesImpl fixture = new FixturesImpl(listOfClubs);
        assertTrue(listOfClubs.contains(roma));
        for (final Club club : listOfClubs) {
            System.out.println("" + club.getName() + "\n");
        }
        assertNotNull(fixture);
        assertEquals(4, listOfClubs.size());
        while (fixture.getNextMatch()!= null){
            if (fixture.getCurrentMatch().e1().getName() == INTER || fixture.getCurrentMatch().e2().getName() == INTER){
                intercount++;
            }
            if (fixture.getCurrentMatch().e1().getName() == ROMA || fixture.getCurrentMatch().e2().getName() == ROMA){
                romacount++;
            }
            if (fixture.getCurrentMatch().e1().getName() == NAPOLI || fixture.getCurrentMatch().e2().getName() == NAPOLI){
                napolicount++;
            }
            if (fixture.getCurrentMatch().e1().getName() == JUVENTUS || fixture.getCurrentMatch().e2().getName() == JUVENTUS){
                juventuscount++;
            }
        }
        assertEquals(6, intercount);
        assertEquals(6, napolicount);
        assertEquals(6, romacount);
        assertEquals(6, juventuscount);
        System.out.println(fixture);
    }

}