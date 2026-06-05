package giocoscudetto.controller;

import java.util.ArrayList;
import java.util.List;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.MatchControllerImpl;

/**
 * Test for {@link giocoscudetto.controller.impl.MatchControllerImpl}.
 */
public class TestMatchControllerImpl {

    private static final String ROMA = "roma";
    private static final String INTER = "inter";
    private static final String NAPOLI = "napoli";
    private static final String JUVENTUS = "juventus";

    private CreateUpdateController updateController = new CreateUpdateControllerImpl();

    void setUp(){
        final List<String> listOfClubs = new ArrayList<>();
        listOfClubs.add(ROMA);
        listOfClubs.add(INTER);
        listOfClubs.add(NAPOLI);
        listOfClubs.add(JUVENTUS);

        final List<Integer> listOfColors = new ArrayList<>();
        listOfColors.add(1);
        listOfColors.add(2);
        listOfColors.add(3);
        listOfColors.add(4);

        updateController.createClubs(listOfClubs, listOfColors);
        MatchController matchController = new MatchControllerImpl(updateController);
    }
}
