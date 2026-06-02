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

    @Test
    void testBackToStartBox() {

        final Club home = new ClubImpl("Roma", new PawnImpl(1));
        final Club away = new ClubImpl("Inter", new PawnImpl(2));

        final MatchImpl match = new MatchImpl(home, away);

        final Club current = match.getCurrentPlayer();
        current.getPawn().setPosition(12);
        BackToStartBox box = new BackToStartBox(15);
        box.event(match);
        assertEquals(0, current.getPawn().getPosition());
    }
}