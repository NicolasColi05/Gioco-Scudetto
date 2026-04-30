package giocoscudetto.controller.impl;

import java.awt.Image;

import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.Starter;
import giocoscudetto.model.api.Board;
import giocoscudetto.model.impl.BoardImpl;
import giocoscudetto.view.api.ViewManager;

/**
 * Starter implementation.
 */
public class StarterImpl implements Starter {

    private final ViewManager viewManager;
    private final Board board = new BoardImpl();

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
}
