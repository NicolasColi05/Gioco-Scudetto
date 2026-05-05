package giocoscudetto.controller.impl;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.Starter;
import giocoscudetto.model.api.Board;
import giocoscudetto.model.api.GoalNet;
import giocoscudetto.model.impl.BoardImpl;
import giocoscudetto.model.impl.GoalNetImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.view.api.ViewManager;
import giocoscudetto.model.api.Match;

/**
 * Starter implementation.
 */
public class StarterImpl implements Starter {

    private final ViewManager viewManager;
    private final Board board = new BoardImpl();
    private final Match match = new MatchImpl();
    private final GoalNet net = new GoalNetImpl();

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
    public Image getBoxImage(int i) {
        return this.board.getBoxImage(i);
    }

    @Override
    public String getScore() {
        return this.match.getScore().toString();
    }

    @Override
    public Color getHomeTeamColor() {
        return this.match.getClubHome().getColor();
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
    public Color getGuestTeamColor() {
        return this.match.getClubAway().getColor();
    }

    @Override
    public int getGuestPosition() {
        return this.match.getClubAway().getPawn().getPosition();
    }

    @Override
    public boolean kickPenalty() {
        if (this.net.isGoal(new Random().nextInt(6) + 1)) {
            if (this.match.getCurrentPlayer().equals(this.match.getClubHome())) {
                this.match.getScore().increaseHomeScore();
            } else {
                this.match.getScore().increaseGuestScore();
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
}
