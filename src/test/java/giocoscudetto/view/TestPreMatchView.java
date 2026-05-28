package giocoscudetto.view;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.MatchControllerImpl;

public class TestPreMatchView extends JFrame{
    
    public TestPreMatchView() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 670);
        CreateUpdateController controller = new CreateUpdateControllerImpl();
        MatchController matchController = new MatchControllerImpl(controller);
        JPanel pa = new giocoscudetto.view.impl.BoardPanel(matchController);
        this.setContentPane(pa);
        pa.paint(this.getGraphics());
        this.setVisible(true);
        
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TestPreMatchView();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });

    }
}