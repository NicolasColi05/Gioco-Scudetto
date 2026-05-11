package giocoscudetto.controller.impl;

import java.util.Random;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.Starter;
import giocoscudetto.model.api.Board;
import giocoscudetto.model.api.GoalNet;
import giocoscudetto.model.impl.BoardImpl;
import giocoscudetto.model.impl.GoalNetImpl;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.impl.FixturesImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.TableImpl;
import giocoscudetto.view.api.ViewManager;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Table;

/**
 * Starter implementation.
 */
public class StarterImpl implements Starter {

    private final ViewManager viewManager;
    private final Board board = new BoardImpl();
    private final Match match = new MatchImpl();
    private final GoalNet net = new GoalNetImpl();
    private final ArrayList<Club> listofClubs = new ArrayList<Club>();
    private Fixtures fixture;
    private int numberOfClubs;
    private Table table;

    /**
     * Constructor for StarterImpl.
     * 
     * @param manager the view manager to use for the controller.
     */
    public StarterImpl(final ViewManager manager) {
        this.viewManager = manager;
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

    @Override
    public void checkBox() {
        
    }

    @Override
    public String getBoxImage(int i) {
        return this.board.getBoxImage(i);
    }

    @Override
    public String getScore() {
        return this.match.getScore().toString();
    }

    @Override
    public int getHomePosition() {
        return this.match.getClubHome().getPawn().getPosition();
    }

    @Override
    public void setKeeperPosition(int i) {
        this.net.setGoalKeeperPosition(i);
    }

    @Override
    public int getGuestPosition() {
        return this.match.getClubAway().getPawn().getPosition();
    }

    @Override
    public boolean kickPenalty() {
        if (this.net.isGoal(new Random().nextInt(6) + 1)) {
            if (this.match.getCurrentPlayer().equals(this.match.getClubHome())) {
                this.match.goalHome();
            } else {
                this.match.goalAway();
            }
            return true;
        }
        return false;
    }

    @Override
    public String getCurrentPlayer() {
        return this.match.getCurrentPlayer().getName();
    }

    @Override
    public void move() {
        this.match.getCurrentPlayer().getPawn().changePosition(this.match.rollDice());
    }
    public void setNumberOfClubs(int n) {
        this.numberOfClubs = n;
    }

    @Override
    public void setClubs(ArrayList<String> names, ArrayList<String> pawns) {
        for (int i = 0; i<numberOfClubs; i++) {
            //listofClubs.add(new ClubImpl(names.get(i), pawns.get(i)));
            //da sistemare per pawn
        }
    }

    private void setFixture(){
        this.fixture = new FixturesImpl(listofClubs);
    }

     @Override
    public Fixtures getFixture() {
       return this.fixture;
    }

    private void setTable(){
        this.table = new TableImpl(listofClubs);
    }

    @Override
    public Table getTable(){
        return this.table;
    }

    @Override
    public String getDescription() {
        return this.board.getBox(this.match.getCurrentPlayer().getPawn().getPosition()).getDescription();
    }

    
}
