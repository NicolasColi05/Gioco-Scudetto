package giocoscudetto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.MatchControllerImpl;
import giocoscudetto.model.api.Match;

/**
 * Test for {@link giocoscudetto.controller.impl.MatchControllerImpl}.
 */
class TestMatchControllerImpl {

    private static final String ROMA = "roma";
    private static final String INTER = "inter";
    private static final String NAPOLI = "napoli";
    private static final String JUVENTUS = "juventus";

    private CreateUpdateController updateController = new CreateUpdateControllerImpl();
    private MatchController matchController;
    private List<String> listOfClubs;
    private String currentClub;

    @BeforeEach
    void setUp() {
        listOfClubs = new ArrayList<>();
        listOfClubs.add(ROMA);
        listOfClubs.add(INTER);
        listOfClubs.add(NAPOLI);
        listOfClubs.add(JUVENTUS);

        final List<Integer> listOfColors = new ArrayList<>();
        listOfColors.add(1);
        listOfColors.add(2);
        listOfColors.add(3);
        listOfColors.add(4);

        this.updateController.createClubs(listOfClubs, listOfColors);
        this.matchController = new MatchControllerImpl(updateController);
    }

    @Test
    void test() {
        matchController.setMatch();
        assertEquals(updateController.getFixture().getCurrentMatch().getClubHome().getName(),
                    this.matchController.getHomeName());
        assertEquals(updateController.getFixture().getCurrentMatch().getClubAway().getName(), 
                    this.matchController.getGuestName());
        this.matchController.setHelpFlag(true);
        assertTrue(this.matchController.isHelpFlag());
        assertEquals(Match.GameMode.NONE.name(), this.matchController.getGameMode());
        for (final String club : listOfClubs) {
            if (club.equals(this.matchController.getCurrentPlayer())) {
                this.currentClub = club;
            }
        }
        final int dice = this.matchController.move();
        assertEquals(this.updateController.getFixture().getCurrentMatch().getCurrentPlayer().getPawn().getPosition(), dice);

        this.matchController.checkBox();

    }
}
