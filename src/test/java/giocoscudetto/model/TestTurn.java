package giocoscudetto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.TurnImpl;

public class TestTurn {

    @Test
    void testChooseStartingPlayer() {

        Club club1 = new ClubImpl("Roma", new PawnImpl(1));
        Club club2 = new ClubImpl("Inter", new PawnImpl(2));

        TurnImpl turn = new TurnImpl(club1, club2);

        assertTrue(
            turn.getCurrentPlayer() == club1
            || turn.getCurrentPlayer() == club2
        );
    }

    @Test
    void testSwitchTurn() {

        Club club1 = new ClubImpl("Roma", new PawnImpl(1));
        Club club2 = new ClubImpl("Inter", new PawnImpl(2));

        TurnImpl turn = new TurnImpl(club1, club2);

        Club first = turn.getCurrentPlayer();

        turn.switchTurn();

        Club second = turn.getCurrentPlayer();

        assertTrue(first != second);
    }

    @Test
    void testSkipTurn() {

        Club club1 = new ClubImpl("Roma", new PawnImpl(1));
        Club club2 = new ClubImpl("Inter", new PawnImpl(2));

        TurnImpl turn = new TurnImpl(club1, club2);

        Club current = turn.getCurrentPlayer();

        turn.setSkipTurn(current);

        turn.switchTurn();

        assertEquals(current, turn.getCurrentPlayer());
    }
}
