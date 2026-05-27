package giocoscudetto.view;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.Starter;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.StarterImpl;
import giocoscudetto.view.impl.MatchPanel;
import giocoscudetto.view.impl.ViewManagerImpl;

public class TestMatchPanel extends JFrame{
    
    public TestMatchPanel() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 670);
        CreateUpdateController controller = new CreateUpdateControllerImpl();
        controller.createClubs(List.of("juve","inter"), List.of(1,2));
        Starter contro = new StarterImpl(null, controller);
        ViewManagerImpl viewManager = new ViewManagerImpl();
        JPanel pa = new MatchPanel(contro, viewManager);
        this.setContentPane(pa);
        this.setVisible(true);
    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                new TestMatchPanel();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });

    }
}
