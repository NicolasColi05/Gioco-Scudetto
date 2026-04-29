package giocoscudetto;

import giocoscudetto.controller.api.Starter;
import giocoscudetto.controller.impl.StarterImpl;
import giocoscudetto.view.api.ViewManager;
import giocoscudetto.view.impl.ClubPanel;
import giocoscudetto.view.impl.HomePanel;
import giocoscudetto.view.impl.MainFrame;
import giocoscudetto.view.impl.PreMatchView;
import giocoscudetto.view.impl.ViewManagerImpl;

public class GameStarter {
    public static void main(String[] args) {

        //Creating the View Manger 
        final ViewManager viewManager = new ViewManagerImpl();

        //Creating the controller to control the game start and panel change
        final Starter controller = new StarterImpl(viewManager);

        //Creating the Views that we will use during the game
        final HomePanel homeView = new HomePanel(controller);
        final ClubPanel clubView = new ClubPanel(controller);
        final PreMatchView preMatch = new PreMatchView(controller);

        //Adding the views to the manager
        viewManager.addView(homeView, "home");
        viewManager.addView(clubView, "club");
        viewManager.addView(preMatch, "pre");

        //Creating the MainFrame
        final MainFrame mainFrame =  new MainFrame(viewManager);

        //Starting game
        controller.startGame();


        

    }
}
