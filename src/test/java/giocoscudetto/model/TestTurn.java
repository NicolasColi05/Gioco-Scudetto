package giocoscudetto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.TurnImpl;

/**
 * Test for TestTurn.
 */
public class TestTurn {

    private static final String ROMA = "Roma";
    private static final String INTER = "Inter";

    private static final int PAWN_ROMA = 1;
    private static final int PAWN_INTER = 2;

    @Test
    void testChooseStartingPlayer() {

        final Club club1 = new ClubImpl(ROMA, PAWN_ROMA);
        final Club club2 = new ClubImpl(INTER, PAWN_INTER);

        final TurnImpl turn = new TurnImpl(club1, club2);

        assertTrue(
            turn.getCurrentPlayer() == club1
            || turn.getCurrentPlayer() == club2
        );
    }

    @Test
    void testSwitchTurn() {

        final Club club1 = new ClubImpl(ROMA, PAWN_ROMA);
        final Club club2 = new ClubImpl(INTER, PAWN_INTER);

        final TurnImpl turn = new TurnImpl(club1, club2);

        final Club first = turn.getCurrentPlayer();

        turn.switchTurn();

        final Club second = turn.getCurrentPlayer();

        assertTrue(first != second);
    }

    @Test
    void testSkipTurn() {

        final Club club1 = new ClubImpl(ROMA, PAWN_ROMA);
        final Club club2 = new ClubImpl(INTER, PAWN_INTER);

        final TurnImpl turn = new TurnImpl(club1, club2);

        final Club current = turn.getCurrentPlayer();

        turn.setSkipTurn(current);

        turn.switchTurn();

        assertEquals(current, turn.getCurrentPlayer());
    }
}
