package giocoscudetto.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Scoreboard;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.FixturesImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.view.impl.result.FixtureModel;

class TestFixtureModel {

    private static final String ROMA = "roma";
    private static final String INTER = "inter";
    private static final String NAPOLI = "napoli";
    private static final String JUVENTUS = "juventus";

    private List<Club> listOfClubs;
    private Fixtures fixture;
    private FixtureModel fixtureModel;

    @BeforeEach
    void setUp() {
        listOfClubs = new ArrayList<>();
        listOfClubs.add(new ClubImpl(ROMA, new PawnImpl(1)));
        listOfClubs.add(new ClubImpl(INTER, new PawnImpl(1)));
        listOfClubs.add(new ClubImpl(NAPOLI, new PawnImpl(1)));
        listOfClubs.add(new ClubImpl(JUVENTUS, new PawnImpl(1)));
        fixture = new FixturesImpl();
        fixture.fixtureGeneration(listOfClubs);
        fixtureModel = new FixtureModel(fixture);
    }

    /**
     * Tests the getCount method.
     */
    @Test
    void testGetCount() {
        assertEquals(2, fixtureModel.getColumnCount());
        assertEquals(listOfClubs.size() * (listOfClubs.size() - 1), fixtureModel.getRowCount());
    }

    /**
     * Tests the getColumnName method.
     */
    @Test
    void testGetColumnName() {
        assertEquals("Match", fixtureModel.getColumnName(0));
        assertEquals("Score", fixtureModel.getColumnName(1));
    }

    /**
     * Tests the getValueAt method.
     */
    @Test
    void testGetValueAt() {
        int count = 0;
        fixture.setNextMatch();
        Match match = fixture.getCurrentMatch();
        while (fixture.seeNextMatch(match) != null) {
            assertEquals(match.toString(), fixtureModel.getValueAt(count, 0).toString());
            fixture.setNextMatch();
            match = fixture.getCurrentMatch();
            count++;
        }
    }

    /**
     * Tests the getColumnClass method.
     */
    @Test
    void testGetColumnClass() {
        assertEquals(Match.class, fixtureModel.getColumnClass(0));
        assertEquals(Scoreboard.class, fixtureModel.getColumnClass(1));
    }

}
