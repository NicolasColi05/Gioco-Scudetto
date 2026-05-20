package giocoscudetto.model;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.StarterImpl;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.PawnImpl;

public class TestEndGameView extends JFrame {

public TestEndGameView() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(650, 670);
    var cr=new CreateUpdateControllerImpl();
    cr.createClubs(List.of("Milan","Juve","Roma","Inter"), List.of(255,128,10,255,128,10));
    var starter=new StarterImpl(null, cr);
    cr.getTable().showPosition().get(0).changeNetDiffs(12, 10);
    
    starter.setMatch();
    
    starter.addPoints();
    JPanel pa = new giocoscudetto.view.impl.EndGameView(starter, "Roma");
    this.setContentPane(pa);
     this.setVisible(true);
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new TestEndGameView());

 }
}

