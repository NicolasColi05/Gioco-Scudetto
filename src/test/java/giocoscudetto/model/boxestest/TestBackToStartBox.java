package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.boxes.BackToStartBox;


/**
 * Tests for BackToStartBox.
 */
public class TestBackToStartBox {

    private static final int START_POSITION = 0;
    private static final int PLAYER_POSITION = 12;
    private static final int BOX_POSITION = 15;

    @Test
    void testBackToStartBox() {

        final Club home = new ClubImpl("Roma", new PawnImpl(1));
        final Club away = new ClubImpl("Inter", new PawnImpl(2));

        final MatchImpl match = new MatchImpl(home, away);

        final Club current = match.getCurrentPlayer();
        current.getPawn().setPosition(PLAYER_POSITION);
        BackToStartBox box = new BackToStartBox(BOX_POSITION);
        box.event(match);
        assertEquals(START_POSITION, current.getPawn().getPosition());
    }
}