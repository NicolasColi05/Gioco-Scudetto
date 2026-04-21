package gioco_scudetto.controller.impl;

import gioco_scudetto.controller.api.Starter;
import gioco_scudetto.view.impl.ClubSelectionView;
//import gioco_scudetto.view.impl.HomeView;
import gioco_scudetto.view.impl.MainFrame;

public class StarterImpl implements Starter{
    
    @Override
    public void startGame() {
        new MainFrame(this);
    }

}
