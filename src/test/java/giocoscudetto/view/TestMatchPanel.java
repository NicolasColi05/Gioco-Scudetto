package giocoscudetto.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.impl.StarterImpl;

public class TestMatchPanel extends JFrame{
    
    public TestMatchPanel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 670);
        JPanel pa = new giocoscudetto.view.impl.MatchPanel(new StarterImpl(null));
        this.setContentPane(pa);
        this.setVisible(true);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TestMatchPanel());

    }
}
