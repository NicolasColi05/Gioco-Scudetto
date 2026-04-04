package gioco_scudetto.controller.impl;

import gioco_scudetto.controller.api.Starter;
import gioco_scudetto.view.impl.HomeView;

public class StarterImpl implements Starter{
    
    @Override
    public void startGame() {
        new HomeView(this);
    }

}
