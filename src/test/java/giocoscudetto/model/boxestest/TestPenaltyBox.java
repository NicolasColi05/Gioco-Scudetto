package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.*;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.boxes.PenaltyBox;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.boxes.PenaltyBox}.
 */
public class TestPenaltyBox {
    private Club clubHome;
    private Club clubAway;
    private Match match;
    private PenaltyBox penaltyBox;

    @BeforeEach
    public void setUp() {
        clubHome = new ClubImpl("home", null);
        clubAway = new ClubImpl("away", null);
        match = new MatchImpl(clubHome, clubAway);
        penaltyBox = new PenaltyBox(5);
        if(match.getCurrentPlayer() != clubHome) {
            match.turn();
        }
    }

    @Test
    public void testEventSetsGameModeToPenalty() {
        assertNotEquals("PENALTY", match.getGameMode());
        penaltyBox.event(match);
        assertEquals("PENALTY", match.getGameMode());
    }

    @Test
    public void testPenaltyBoxPosition() {
        assertEquals(5, penaltyBox.getPosition());
    }

    @Test
    public void testPenaltyBoxName() {
        assertEquals("Penalty Box", penaltyBox.getName());
    }

    @Test
    public void testPenaltyBoxImage() {
        assertEquals("casella_16.png", penaltyBox.getImage());
    }
}
