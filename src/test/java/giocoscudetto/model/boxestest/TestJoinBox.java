package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.boxes.JoinBox;


public class TestJoinBox {

    @Test
    void testJoinBox() {

        Club home = new ClubImpl("Roma", new PawnImpl(1));
        Club away = new ClubImpl("Inter", new PawnImpl(2));

        MatchImpl match = new MatchImpl(home, away);
        home.getPawn().setPosition(5);
        away.getPawn().setPosition(12);

        JoinBox box = new JoinBox(17);

        box.event(match);
        match.turn();
        assertEquals(away.getPawn().getPosition(), home.getPawn().getPosition());
    }
    
}
