package giocoscudetto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.ClubImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.controller.impl.CreateUpdateControllerImpl}.
 */
public class TestCreateUpdateController {
    
    private final CreateUpdateController controller = new CreateUpdateControllerImpl();
    
    @Test
    void testClubCreation() {
        initialConfiguration();

        assertNotNull(controller.getClubs());
        assertNotNull(controller.getTable());
        assertNotNull(controller.getFixture());

        //Testing clubs 
        assertEquals(3, this.controller.getClubs().size());
        assertTrue(this.controller.getClubs().stream().anyMatch(p -> "Inter".equals(p.getName())));
        assertTrue(this.controller.getClubs().stream().anyMatch(p -> "Milan".equals(p.getName())));
        assertTrue(this.controller.getClubs().stream().anyMatch(p -> "Juve".equals(p.getName())));        

        //Testing table
        assertEquals(3, this.controller.getTable().showPosition().size());
        assertTrue(this.controller.getTable().showPosition().stream().anyMatch(p -> "Inter".equals(p.getName())));
        assertTrue(this.controller.getTable().showPosition().stream().anyMatch(p -> "Milan".equals(p.getName())));
        assertTrue(this.controller.getTable().showPosition().stream().anyMatch(p -> "Juve".equals(p.getName())));        

    }

    private void initialConfiguration() {

        final Club club1 = new ClubImpl("Inter", null);
        final Club club2 = new ClubImpl("Milan", null);
        final Club club3 = new ClubImpl("Juve", null);

        this.controller.createClubs(List.of(club1.getName(), club2.getName(), club3.getName()));

    }

}
