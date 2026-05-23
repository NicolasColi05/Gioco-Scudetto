package giocoscudetto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.impl.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Simple test for {@link giocoscudetto.model.impl.FixturesImpl}.
 */
public class TestFixtures {

    private final static String ROMA = "roma";
    private final static String INTER = "inter";
    private final static String NAPOLI = "napoli";
    private final static String JUVENTUS = "juventus";

    private Club roma;
    private Club inter;
    private Club napoli;
    private Club juventus;
    private List<Club> listOfClubs;
    private FixturesImpl fixture;

    @BeforeEach
    void setUp() {
        roma = new ClubImpl(ROMA, new PawnImpl(1));
        inter = new ClubImpl(INTER, new PawnImpl(1));
        napoli = new ClubImpl(NAPOLI, new PawnImpl(1));
        juventus = new ClubImpl(JUVENTUS, new PawnImpl(1));
        listOfClubs = List.of(roma, inter, napoli, juventus);
        fixture = new FixturesImpl();
    }

    @Test
    void testFixtureGenerationMatchCount(){
        fixture.fixtureGeneration(listOfClubs);
        assertEquals(12 , fixture.getListOfMatches().size());
    }

    @Test
    void testFixturesGenerationClubCount() {
        int intercount = 0;
        int romacount = 0;
        int napolicount = 0;
        int juventuscount = 0;
        fixture.fixtureGeneration(listOfClubs);
        assertNotNull(fixture);
        assertEquals(4, listOfClubs.size());
        while (fixture.setNextMatch()!= null){
            if (fixture.getCurrentMatch().getClubHome().getName() == INTER || fixture.getCurrentMatch().getClubAway().getName() == INTER){
                intercount++;
            }
            if (fixture.getCurrentMatch().getClubHome().getName() == ROMA || fixture.getCurrentMatch().getClubAway().getName() == ROMA){
                romacount++;
            }
            if (fixture.getCurrentMatch().getClubHome().getName() == NAPOLI || fixture.getCurrentMatch().getClubAway().getName() == NAPOLI){
                napolicount++;
            }
            if (fixture.getCurrentMatch().getClubHome().getName() == JUVENTUS || fixture.getCurrentMatch().getClubAway().getName() == JUVENTUS){
                juventuscount++;
            }
        }
        assertEquals(6, intercount);
        assertEquals(6, napolicount);
        assertEquals(6, romacount);
        assertEquals(6, juventuscount);
        System.out.println(fixture);
    }

    @Test
    void testIsEmpty(){
        assertTrue(fixture.isEmpty());
        fixture.fixtureGeneration(listOfClubs);
        assertFalse(fixture.isEmpty());
        fixture.resetFixture();
        fixture.isEmpty();
    }

    @Test
    void testResetFixture() {
        fixture.fixtureGeneration(listOfClubs);
        fixture.resetFixture();
        assertTrue(fixture.isEmpty());
    }

    @Test
    void testNextMatch(){
        fixture.fixtureGeneration(listOfClubs);
        Match match = fixture.getCurrentMatch();
        assertEquals(fixture.seeNextMatch(match), fixture.setNextMatch());
        fixture.resetFixture();
        fixture.fixtureGeneration(listOfClubs);
        int count = 0;
        while (fixture.setNextMatch() != null) {
            count++;
        }
        assertEquals(12, count);
    }


}