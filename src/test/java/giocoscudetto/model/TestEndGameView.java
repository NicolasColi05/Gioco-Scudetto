package giocoscudetto.model;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.impl.StarterImpl;

public class TestEndGameView extends JFrame {

public TestEndGameView() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(650, 670);
    JPanel pa = new giocoscudetto.view.impl.EndGameView(new StarterImpl(null, null));
    this.setContentPane(pa);
     this.setVisible(true);
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new TestEndGameView());

 }
}

