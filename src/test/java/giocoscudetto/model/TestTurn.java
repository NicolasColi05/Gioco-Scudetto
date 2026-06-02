package giocoscudetto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.TurnImpl;

/**
 * Test for TestTurn.
 */
public class TestTurn {

    @Test
    void testChooseStartingPlayer() {

        final Club club1 = new ClubImpl("Roma", new PawnImpl(1));
        final Club club2 = new ClubImpl("Inter", new PawnImpl(2));

        final TurnImpl turn = new TurnImpl(club1, club2);

        assertTrue(
            turn.getCurrentPlayer() == club1
            || turn.getCurrentPlayer() == club2
        );
    }

    @Test
    void testSwitchTurn() {

        final Club club1 = new ClubImpl("Roma", new PawnImpl(1));
        final Club club2 = new ClubImpl("Inter", new PawnImpl(2));

        final TurnImpl turn = new TurnImpl(club1, club2);

        final Club first = turn.getCurrentPlayer();

        turn.switchTurn();

        final Club second = turn.getCurrentPlayer();

        assertTrue(first != second);
    }

    @Test
    void testSkipTurn() {

        final Club club1 = new ClubImpl("Roma", new PawnImpl(1));
        final Club club2 = new ClubImpl("Inter", new PawnImpl(2));

        final TurnImpl turn = new TurnImpl(club1, club2);

        final Club current = turn.getCurrentPlayer();

        turn.setSkipTurn(current);

        turn.switchTurn();

        assertEquals(current, turn.getCurrentPlayer());
    }
}
