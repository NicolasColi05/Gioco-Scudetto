package giocoscudetto.model;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestFixtures {
    final Club roma = new ClubImpl(new PawnImpl());
    final Club inter = new ClubImpl(new PawnImpl());
    final Club napoli = new ClubImpl(new PawnImpl());
    //final Club juventus = new ClubImpl(new PawnImpl());
    final ArrayList<Club> listOfClubs = new ArrayList<>();

    @Test
    void TestFixturesGeneration() {
        roma.setName("roma");
        inter.setName("inter");
        napoli.setName("napoli");
        //juventus.setName("juventus");
        listOfClubs.add(roma);
        listOfClubs.add(inter);
        listOfClubs.add(napoli);
        //listOfClubs.add(juventus);
        final FixturesImpl fixture = new FixturesImpl(listOfClubs);
        for (final Club club : listOfClubs) {
            System.out.println("" + club.getName() + "\n");
        }
        assertNotNull(fixture);
        assertEquals(3, listOfClubs.size());
        System.out.println(fixture);
    }

}