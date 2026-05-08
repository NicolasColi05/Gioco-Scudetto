package giocoscudetto.controller.impl;

import java.util.LinkedList;
import java.util.List;

import giocoscudetto.controller.api.Controller;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Table;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.TableImpl;


/**
 * Implementation of controller interface
 */
public class ControllerImpl implements Controller {

    private final List<Club> clubs = new LinkedList<>();
    private Table table; //Andrebbe final, ma dovrei definirlo in un costruttore
    /**
     * {@inheritDoc}
     */
    @Override
    public void createClubs(List<String> clubsName) {
        
        for (String name : clubsName) {
            clubs.add(new ClubImpl(name, new PawnImpl("red")));
        }

        this.table = new TableImpl(clubs);

    }

    
}
