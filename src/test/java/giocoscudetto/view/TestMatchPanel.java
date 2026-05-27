package giocoscudetto.view;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.Starter;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.StarterImpl;
import giocoscudetto.view.api.ViewManager;
import giocoscudetto.view.impl.MatchPanel;
import giocoscudetto.view.impl.ViewManagerImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 */
/**
 * Test for {@link giocoscudetto.view.impl.MatchPanel}.
 */
public class TestMatchPanel extends JFrame {

    /**
     * Initializes the test frame with a MatchPanel.
     *
     * @throws IOException if loading an image fails.
     */
    public TestMatchPanel() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);

        final CreateUpdateController controller = new CreateUpdateControllerImpl();
        controller.createClubs(List.of("Inter", "Milan"), List.of(0, 0xFF0000));

        final ViewManager viewManager = new ViewManagerImpl();
        final Starter starter = new StarterImpl(viewManager, controller);
        starter.setMatch();

        final MatchPanel matchPanel = new MatchPanel(starter, viewManager);
        this.setContentPane(matchPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
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
                e.printStackTrace();
            }
        });
    }
}
