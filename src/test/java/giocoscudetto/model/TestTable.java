package giocoscudetto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Table;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.TableImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.TableImpl}.
 */
public class TestTable {
    
private Club inter;
   
    private Club juve;
    private Club bologna;
    private Table rank;

    @BeforeEach
    void setUp() {
        inter = new ClubImpl("Inter", new PawnImpl(Color.BLUE)); 
        juve = new ClubImpl("Juve", new PawnImpl(Color.BLACK)); 
        bologna = new ClubImpl("Bologna", new PawnImpl(Color.RED)); 
        rank = new TableImpl(List.of(bologna, inter, juve));
    }

    @Test
    void testCorrectClubsPosition() {
        initialConfiguration();

        //After getting the initial configuration, we update Clubs rank position
        rank.updateClubRank();
        assertEquals(rank.showPosition(), List.of(inter, juve, bologna));

        //If points are the same net diff matter
        bologna.incrementPoints(3);
        rank.updateClubRank();
        assertEquals(rank.showPosition(), List.of(bologna, inter, juve));
    }

    private void initialConfiguration() {
        //Inter with 4 point and 1 net diff
        inter.incrementPoints(4);
        inter.changeNetDiffs(4, 3);

        //Juve with 3 points and 0 net diff
        juve.incrementPoints(3);
        juve.changeNetDiffs(2, 2);

        //Bologna with 1 point and 3 net diff
        bologna.incrementPoints(1);
        bologna.changeNetDiffs(5, 2);
    }
}
