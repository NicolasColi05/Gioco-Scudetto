package gioco_scudetto.controller.impl;

import gioco_scudetto.controller.api.Starter;
import gioco_scudetto.view.api.ViewManager;
//import gioco_scudetto.view.impl.HomeView;

public class StarterImpl implements Starter {

    private final ViewManager viewManager;

    public StarterImpl(final ViewManager manager) {
        this.viewManager = manager;
    }

    @Override
    public void startGame() {
        this.viewManager.showView("home");
    }

    @Override
    public void changeView(final String panelName) {
        this.viewManager.showView(panelName);
    }


}
