package giocoscudetto.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.api.Starter;
import giocoscudetto.controller.impl.StarterImpl;

public class TestMatchPanel extends JFrame{
    
    public TestMatchPanel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 670);
        Starter s = new StarterImpl(null);
        s.setClubs(new ArrayList<>(List.of("Fede","Nico")), new ArrayList<>(List.of("giallo","rosso")));
        JPanel pa = new giocoscudetto.view.impl.MatchPanel(s);
        this.setContentPane(pa);
        this.setVisible(true);
    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new TestMatchPanel());

    }
}
