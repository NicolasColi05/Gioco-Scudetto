package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.boxes.JoinBox;

/**
 * Tests for JoinBox.
 */
public class TestJoinBox {

    @Test
    void testJoinBox() {

        final Club home = new ClubImpl("Roma", new PawnImpl(1));
        final Club away = new ClubImpl("Inter", new PawnImpl(2));

        final MatchImpl match = new MatchImpl(home, away);
        home.getPawn().setPosition(5);
        away.getPawn().setPosition(12);

        final JoinBox box = new JoinBox(17);

        box.event(match);
        match.turn();
        assertEquals(away.getPawn().getPosition(), home.getPawn().getPosition());
    }   
}
