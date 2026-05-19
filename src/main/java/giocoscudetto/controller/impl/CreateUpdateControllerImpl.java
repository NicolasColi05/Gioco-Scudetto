package giocoscudetto.controller.impl;

import java.util.LinkedList;
import java.util.List;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Table;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.FixtureModel;
import giocoscudetto.model.impl.FixturesImpl;
import giocoscudetto.model.impl.PawnImpl;
import giocoscudetto.model.impl.TableImpl;
import giocoscudetto.model.impl.TableModel;


/**
 * Implementation of CreateController interface
 */
public class CreateUpdateControllerImpl implements CreateUpdateController {

    private final List<Club> clubs = new LinkedList<>();
    private final Table table = new TableImpl();
    private final Fixtures fixture = new FixturesImpl();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClubNameComplete(final List<String> clubsName) {
        //Removing spaces from names, to avoid equal names that jst differs for some spaces
        final List<String> namesWithoutSpaces = clubsName.stream()
                        .map(n -> n.replaceAll("\\s+", ""))
                        .toList();
        //namesWithoutSpaces.stream().forEach(n -> System.out.println(n));

        return !namesWithoutSpaces.stream().anyMatch(name -> "".equals(name)) &&
            namesWithoutSpaces.stream().distinct().toList().size() == namesWithoutSpaces.size();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createClubs(final List<String> clubsName, final List<Integer> pawnRGB) {
        
        int i = 0;
        for(; i < clubsName.size(); i++) {
            clubs.add(new ClubImpl(clubsName.get(i), new PawnImpl(pawnRGB.get(i))));
        }

        this.table.addAllClubs(this.clubs);

        this.fixture.fixtureGeneration(this.clubs);        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClubScores(final int pawnId,
        final int points,
        final int goalScored,
        final int goalConceded) {
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
        System.out.println("fixture" + this.fixture.toString());
        return this.fixture;
    }

    @Override
    public void reset(){
        this.clubs.clear();
        this.fixture.resetFixture();
        this.table.reset();
    }
    
    @Override
    public FixtureModel getFixtureTableModel(){
        FixtureModel model = new FixtureModel(this.getFixture());
        return model;
    }

    @Override
    public TableModel getLeagueTableModel(){
        this.table.updateClubRank();
        TableModel model = new TableModel(this.getTable());
        return model;
    }
}
