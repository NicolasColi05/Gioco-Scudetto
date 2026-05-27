package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.boxes.SkipTurnBox;


public class TestSkipTurnBox {

    @Test
    void testSkipTurnBox() {

        Club home = new ClubImpl("Roma", new PawnImpl(1));
        Club away = new ClubImpl("Inter", new PawnImpl(2));

        MatchImpl match = new MatchImpl(home, away);

        Club current = match.getCurrentPlayer();
        SkipTurnBox box = new SkipTurnBox(21);
        box.event(match);
        match.turn();
        assertEquals(current, match.getCurrentPlayer());
    }
    
}
