package giocoscudetto.view;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.controller.api.Starter;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.StarterImpl;
import giocoscudetto.view.api.ViewManager;
import giocoscudetto.controller.impl.MatchControllerImpl;
import giocoscudetto.view.impl.ViewManagerImpl;
import giocoscudetto.view.impl.match.MatchPanel;

/*
 * CHECKSTYLE: MagicNumber OFF
 */
/**
 * Test for {@link giocoscudetto.view.impl.match.MatchPanel}.
 */
public class TestMatchPanel extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Initializes the test frame with a MatchPanel.
     *
     * @throws IOException if loading an image fails.
     */
    public TestMatchPanel() throws IOException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //NOPMD
        this.setSize(900, 700); //NOPMD

        final CreateUpdateController createUpdateController = new CreateUpdateControllerImpl();
        createUpdateController.createClubs(List.of("Inter", "Milan"), List.of(0, 0xFF0000));

        final ViewManager viewManager = new ViewManagerImpl();
        final MatchController matchController = new MatchControllerImpl(createUpdateController);
        final Starter starter = new StarterImpl(viewManager);
        matchController.setMatch();

        final MatchPanel matchPanel = new MatchPanel(starter, viewManager, createUpdateController, matchController);
        this.setContentPane(matchPanel); //NOPMD
        this.setLocationRelativeTo(null); //NOPMD
        this.setVisible(true); //NOPMD
    }

    /**
     * Main method to execute the MatchPanel test.
     *
     * @param args command line args (not used).
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TestMatchPanel();
            } catch (final IOException e) {
                e.printStackTrace(); //NOPMD
            }
        });
    }
}
