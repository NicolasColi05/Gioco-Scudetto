package giocoscudetto.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Table;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.TableImpl;
import giocoscudetto.view.impl.result.LeagueTableModel;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Simple test for {@link LeagueTableModel}.
 */
class TestLeagueTableModel {

    private static final String ROMA = "roma";
    private static final String INTER = "inter";
    private static final String NAPOLI = "napoli";
    private static final String JUVENTUS = "juventus";

    private Table table;
    private LeagueTableModel leagueTableModel;

    @BeforeEach
    void setUp() {

        final List<Club> listOfClubs = new ArrayList<>();
        listOfClubs.add(new ClubImpl(ROMA, new PawnImpl(1)));
        listOfClubs.add(new ClubImpl(INTER, new PawnImpl(1)));
        listOfClubs.add(new ClubImpl(NAPOLI, new PawnImpl(1)));
        listOfClubs.add(new ClubImpl(JUVENTUS, new PawnImpl(1)));

        listOfClubs.get(0).incrementPoints(4);
        listOfClubs.get(1).incrementPoints(1);
        listOfClubs.get(2).incrementPoints(3);
        listOfClubs.get(3).incrementPoints(6);

        table = new TableImpl();
        table.addAllClubs(listOfClubs);
        table.updateClubRank();
        leagueTableModel = new LeagueTableModel(table);
    }

    /**
     * Tests the getCount method.
     */
    @Test
    void testGetCount() {
        assertEquals(4, leagueTableModel.getRowCount());
        assertEquals(3, leagueTableModel.getColumnCount());

    }

    /**
     * Tests the getColumnName method.
     */
    @Test
    void testGetColumnName() {
        assertEquals("Club", leagueTableModel.getColumnName(0));
        assertEquals("Points", leagueTableModel.getColumnName(1));
        assertEquals("Net Difference", leagueTableModel.getColumnName(2));
    }

    /**
     * 
     */
    @Test
    void testGetValueAt() {
        for (int i = 0; i < 4; i++) {
            assertEquals(table.showPosition().get(i).getName(), leagueTableModel.getValueAt(i, 0).toString());
            assertEquals(table.showPosition().get(i).getPoints(), leagueTableModel.getValueAt(i, 1));
            assertEquals(table.showPosition().get(i).getNetDiff(), leagueTableModel.getValueAt(i, 2));
        }
    }
}
