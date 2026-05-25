package giocoscudetto.view;
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

    cr.getTable().getClubs().get(0).incrementPoints(10);
    cr.getTable().getClubs().get(0).changeNetDiffs(12, 2);

    cr.getTable().getClubs().get(1).incrementPoints(8);
    cr.getTable().getClubs().get(1).changeNetDiffs(10, 5);

    cr.getTable().getClubs().get(2).incrementPoints(6);
    cr.getTable().getClubs().get(2).changeNetDiffs(8, 7);

    cr.getTable().getClubs().get(3).incrementPoints(3);
    cr.getTable().getClubs().get(3).changeNetDiffs(4, 10);

    cr.getTable().updateClubRank();

    
    starter.setMatch();
    
    JPanel pa = new giocoscudetto.view.impl.EndGameView(starter);
    this.setContentPane(pa);
     this.setVisible(true);
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new TestEndGameView());

 }
}

