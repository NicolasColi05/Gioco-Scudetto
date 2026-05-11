package giocoscudetto.controller.impl;

import java.util.LinkedList;
import java.util.List;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Table;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.FixturesImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.TableImpl;


/**
 * Implementation of CreateController interface
 */
public class CreateUpdateControllerImpl implements CreateUpdateController {

    private final List<Club> clubs = new LinkedList<>();
    private final Table table = new TableImpl();
    private Fixtures fixture; //Andrebbe final, ma dovrei definirlo in un costruttore
    /**
     * {@inheritDoc}
     */
    @Override
    public void createClubs(List<String> clubsName) {
        
        for (String name : clubsName) {
            clubs.add(new ClubImpl(name, new PawnImpl(1)));
        }

        this.table.addAllClubs(this.clubs);

        this.fixture = new FixturesImpl(clubs);

    }

    
}
