package giocoscudetto.controller.impl;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.controller.api.Starter;
import giocoscudetto.model.api.Board;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.impl.BoardImpl;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.view.api.ViewManager;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Table;
import giocoscudetto.view.api.GameObserver;


/**
 * Starter implementation.
 */
public class StarterImpl implements Starter {

    private final ViewManager viewManager;
    private final CreateUpdateController controller;
    private final MatchController matchController;
    private final Board board = new BoardImpl();
    private Fixtures fixture;
    private Match match;
    private Table table;
    private List<GameObserver> observers = new ArrayList<>();
    private boolean helpFlag = false;

    /**
     * Constructor for StarterImpl.
     * 
     * @param manager the view manager to use for the controller.
     */
    public StarterImpl(final ViewManager manager, CreateUpdateController controller, MatchController matchController) {
        this.viewManager = manager;
        this.controller = controller;
        this.matchController = matchController;
    }

    @Override
    public void startGame() {
        this.viewManager.showView("home");
    }

    @Override
    public void changeView(final String panelName) {
        SwingUtilities.invokeLater(() -> viewManager.showView(panelName));
        System.out.println("controller");
    }

    @Override
    public void closeGame() {
        this.viewManager.quit();
    }

    /*@Override
    public void addObserver(final GameObserver ob) {
        this.observers.add(ob);
    }

    @Override
    public void removeObserver(final GameObserver ob) {
        this.observers.remove(ob);
    }

    @Override
    public void notifyViews() {
        for (GameObserver ob : observers) {
            ob.updateState();
        }
    }*/

    @Override
    public void resetFixture(){
        this.fixture = this.controller.getFixture();
        this.fixture.resetFixture();
        System.out.println(this.fixture.toString());
        System.out.println("OK");
        this.controller.reset();
        
    }

    @Override
    public void resetTable(){
        /*this.table.reset();
        System.out.println(this.table.toString());
        System.out.println("OK");
        this.controller.reset();/* */
        controller.reset();
    }

    
    public int diceEvent() {
       return this.match.diceEvent();
    }

    public String getWinner() {
        return this.table.getClubs().get(0).getName();
    }

    @Override
    public void restartLeague(){
        List<Club> clubs = controller.getClubs();
        List<Integer> pawns = new ArrayList<>();
        List<String> clubsname = new ArrayList<>();
        for (Club club : clubs) {
            pawns.add(club.getPawn().getPawnRGB());
            clubsname.add(club.getName());
        }
        this.matchController.setPositionsZero();
        this.resetFixture();
        this.resetTable();
        controller.createClubs(clubsname,pawns);
    }

}
