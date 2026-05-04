package giocoscudetto.model;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.*;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void TestFixturesGeneration() {
        roma.setName("roma");
        inter.setName("inter");
        napoli.setName("napoli");
        juventus.setName("juventus");
        listOfClubs.add(roma);
        listOfClubs.add(inter);
        listOfClubs.add(napoli);
        listOfClubs.add(juventus);
        final FixturesImpl fixture = new FixturesImpl(listOfClubs);
        for (final Club club : listOfClubs) {
            System.out.println("" + club.getName() + "\n");
        }
        assertNotNull(fixture);
        assertEquals(4, listOfClubs.size());
        System.out.println(fixture);
    }

}