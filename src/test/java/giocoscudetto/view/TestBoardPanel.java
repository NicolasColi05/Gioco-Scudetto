package giocoscudetto.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.MatchControllerImpl;
import giocoscudetto.controller.impl.StarterImpl;

public class TestBoardPanel extends JFrame{
    
    public TestBoardPanel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 670);
        final CreateUpdateController controller = new CreateUpdateControllerImpl();
        final MatchControllerImpl matchController = new MatchControllerImpl(controller);
        final JPanel pa = new giocoscudetto.view.impl.BoardPanel(new StarterImpl(null, controller, matchController), matchController);
        this.setContentPane(pa);
        pa.paint(this.getGraphics());
        this.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TestBoardPanel());

    }
}