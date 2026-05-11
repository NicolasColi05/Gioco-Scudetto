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
    private Fixtures fixture;
    
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClubScores(final int pawnId, final int points, final int goalScored, final int goalConceded) {
        this.clubs.get(pawnId).changeNetDiffs(goalScored, goalConceded);
        this.clubs.get(pawnId).incrementPoints(points);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClubActualRank() {
        this.table.updateClubRank();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Club> getClubActualRank() {
        return this.table.showPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Club> getClubs() {
        return this.clubs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Table getTable() {
        return this.table;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Fixtures getFixture() {
        return this.fixture;
    }
    
}
