package giocoscudetto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.controller.impl.CreateUpdateControllerImpl}.
 */
public class TestCreateUpdateController {
    
    private final CreateUpdateController controller = new CreateUpdateControllerImpl();
    

    /**
     * Testing some basic condition that sould be avoid before allowing users to create clubs.
     */
    @Test
    public void testClubsCorrectness() {
        
        //Spaces are not allowed in the name, so "s" and " s" are the same name
        assertFalse(controller.isClubInfoComplete(List.of("bologna", " bolo gna "), List.of(true, true)));
        
        //Each Club's name can not be empty
        assertFalse(controller.isClubInfoComplete(List.of("bologna", ""), List.of(true, true)));

        //Each Club's pawn color has to be selected
        assertFalse(controller.isClubInfoComplete(List.of("bologna", "inter"), List.of(false, false)));
        assertFalse(controller.isClubInfoComplete(List.of("bologna", "inter"), List.of(true, false)));

        //If all the info have been correctly choosen, you can proceed creating the clubs
        assertTrue(controller.isClubInfoComplete(List.of("bologna", "inter"), List.of(true, true)));
    } 

    /**
     * Testing club creation goes well, not letting anything null.
     */
    @Test
    public void testClubCreation() {
        controller.createClubs(List.of("Inter", "Bologna", "Imolese"),
                               List.of(32, 48, 53));

        //Testing that clubs have been correctly added to the controller list
        assertEquals(3, this.controller.getClubs().size());
        assertTrue(this.controller.getClubs().stream().anyMatch(p -> "Inter".equals(p.getName())));
        assertTrue(this.controller.getClubs().stream().anyMatch(p -> "Bologna".equals(p.getName())));
        assertTrue(this.controller.getClubs().stream().anyMatch(p -> "Imolese".equals(p.getName())));        

        //Testing that clubs have been correctly added to the controller table
        assertEquals(3, this.controller.getTable().showPosition().size());
        assertTrue(this.controller.getClubActualRank().stream().anyMatch(p -> "Inter".equals(p.getName())));
        assertTrue(this.controller.getClubActualRank().stream().anyMatch(p -> "Bologna".equals(p.getName())));
        assertTrue(this.controller.getClubActualRank().stream().anyMatch(p -> "Imolese".equals(p.getName()))); 
        
        //Testing that clubs have been correctly added to the controller fixture
        assertEquals(6, this.controller.getFixture().getListOfMatches().size());
        assertTrue(this.controller.getFixture().getListOfMatches().stream().anyMatch(m -> "Inter".equals(m.getClubHome().getName())));
        assertTrue(this.controller.getFixture().getListOfMatches().stream().anyMatch(m -> "Bologna".equals(m.getClubHome().getName())));
        assertTrue(this.controller.getFixture().getListOfMatches().stream().anyMatch(m -> "Imolese".equals(m.getClubHome().getName())));
        assertTrue(this.controller.getFixture().getListOfMatches().stream().anyMatch(m -> "Inter".equals(m.getClubAway().getName())));
        assertTrue(this.controller.getFixture().getListOfMatches().stream().anyMatch(m -> "Bologna".equals(m.getClubAway().getName())));
        assertTrue(this.controller.getFixture().getListOfMatches().stream().anyMatch(m -> "Imolese".equals(m.getClubAway().getName()))); 
    }

    /**
     * Tests that it restart the league correctly with the same teams.
     */
    @Test
    public void testRestartLeague() {
        controller.createClubs(List.of("Inter", "Roma", "Juventus"),
                               List.of(25, 3, 14));
        controller.getClubs().get(0).getPawn().setPosition(20);
        controller.getClubs().get(1).getPawn().setPosition(18);
        controller.getClubs().get(2).getPawn().setPosition(36);
        controller.getClubs().get(0).changeNetDiffs(5, 0);
        controller.getClubs().get(1).changeNetDiffs(2, 4);
        controller.getClubs().get(2).changeNetDiffs(1, 4);
        controller.updateClubActualRank();
        final String firstFixture = controller.getFixture().getListOfMatches().toString();
        final String firstTable = controller.getTable().showPosition().toString();
        controller.restartLeague();
        assertNotEquals(firstFixture, controller.getFixture().getListOfMatches().toString());
        assertNotEquals(firstTable, controller.getTable().showPosition().toString());
        assertTrue(controller.getFixture().toString().contains("Inter"));
        assertTrue(controller.getFixture().toString().contains("Roma"));
        assertTrue(controller.getFixture().toString().contains("Juventus"));
        final ArrayList<String> clubsName = new ArrayList<>();
        controller.getTable().showPosition().forEach(club -> clubsName.add(club.getName()));
        assertTrue(clubsName.contains("Inter"));
        assertTrue(clubsName.contains("Roma"));
        assertTrue(clubsName.contains("Juventus"));
    }
}
