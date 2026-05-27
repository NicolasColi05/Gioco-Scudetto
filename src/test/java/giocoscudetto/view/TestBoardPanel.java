package giocoscudetto.view;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.Starter;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.StarterImpl;
import giocoscudetto.view.impl.BoardPanel;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.view.impl.BoardPanel}.
 */
public class TestBoardPanel extends JFrame {

    /**
     * Initializes the test frame with a BoardPanel.
     * @throws IOException
     */
    public TestBoardPanel() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 670);

        final CreateUpdateController controller = new CreateUpdateControllerImpl();
        controller.createClubs(List.of("Inter", "Milan"), List.of(0, 0xFF0000));

        final Starter starter = new StarterImpl(null, controller);
        starter.setMatch();

        final BoardPanel boardPanel = new BoardPanel(starter);
        boardPanel.start();

        this.setContentPane(boardPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * The main method to run the test frame.
     * 
     * @param args the command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TestBoardPanel();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });
    }
}
