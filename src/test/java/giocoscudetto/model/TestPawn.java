package giocoscudetto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import giocoscudetto.model.impl.PawnImpl;

public class TestPawn {

    @Test
    void testInitialPosition() {

        PawnImpl pawn = new PawnImpl(255);

        assertEquals(0, pawn.getPosition());
    }

    @Test
    void testChangePosition() {

        PawnImpl pawn = new PawnImpl(255);

        pawn.changePosition(5);

        assertEquals(5, pawn.getPosition());
    }

    @Test
    void testSetPosition() {

        PawnImpl pawn = new PawnImpl(255);

        pawn.setPosition(12);

        assertEquals(12, pawn.getPosition());
    }

    @Test
    void testPawnRGB() {

        PawnImpl pawn = new PawnImpl(123);

        assertEquals(123, pawn.getPawnRGB());
    }
}
