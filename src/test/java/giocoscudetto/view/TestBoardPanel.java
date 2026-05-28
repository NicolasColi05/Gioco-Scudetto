package giocoscudetto.view;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.view.impl.BoardPanel;
import giocoscudetto.controller.impl.MatchControllerImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.view.impl.BoardPanel}.
 */
public class TestBoardPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    /**
     * Initializes the test frame with a BoardPanel.
     * 
     * @throws IOException if loading an image fails.
     */
    public TestBoardPanel() throws IOException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //NOPMD
        this.setSize(650, 670); //NOPMD

        final CreateUpdateController controller = new CreateUpdateControllerImpl();
        controller.createClubs(List.of("Inter", "Milan"), List.of(0, 0xFF0000));

        final MatchController starter = new MatchControllerImpl(controller);
        starter.setMatch();

        final BoardPanel boardPanel = new BoardPanel(starter);
        boardPanel.start();

        this.setContentPane(boardPanel); //NOPMD
        this.setLocationRelativeTo(null); //NOPMD
        this.setVisible(true); //NOPMD
    }

    /**
     * The main method to run the test frame.
     * 
     * @param args the command line arguments (not used).
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TestBoardPanel();
            } catch (final IOException e) {
                e.printStackTrace(); //NOPMD
            }
        });
    }
}
