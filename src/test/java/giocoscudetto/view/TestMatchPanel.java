package giocoscudetto.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.StarterImpl;

public class TestMatchPanel extends JFrame{
    
    public TestMatchPanel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 670);
        CreateUpdateController controller = new CreateUpdateControllerImpl();
        JPanel pa = new giocoscudetto.view.impl.BoardPanel(new StarterImpl(null, controller));
        this.setContentPane(pa);
        this.setVisible(true);
    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new TestMatchPanel());

    }
}
