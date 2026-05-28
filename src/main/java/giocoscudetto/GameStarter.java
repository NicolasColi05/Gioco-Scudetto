package giocoscudetto;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.Starter;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.MatchControllerImpl;
import giocoscudetto.controller.impl.StarterImpl;
import giocoscudetto.view.api.ViewManager;
import giocoscudetto.view.impl.BotView;
import giocoscudetto.view.impl.HomePanel;
import giocoscudetto.view.impl.MainFrame;
import giocoscudetto.view.impl.PreMatchView;
import giocoscudetto.view.impl.ViewManagerImpl;
import giocoscudetto.view.impl.creation.ClubPanel;

/**
 * This is the main class of the game, it is used to start the game and create the main frame.
 * It also creates some of the views and the controllers and add the views to the manager.
 */
public final class GameStarter {

    private GameStarter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Main method to start the game.
     * 
     * @param args the arguments of the main method.
     */
    public static void main(final String[] args) {

        //Creating the View Manger 
        final ViewManager viewManager = new ViewManagerImpl();

        //Creating the controller to make the view work with model rules
        final CreateUpdateController controller = new CreateUpdateControllerImpl();

        //Creating the controller to operate during the match
        final MatchController matchController = new MatchControllerImpl(controller);

        //Creating the controller to change the panel
        final Starter viewChangerController = new StarterImpl(viewManager, controller, matchController);

        //Creating the Views that we will use during the game
        final HomePanel homeView = new HomePanel(viewChangerController);
        final ClubPanel clubView = new ClubPanel(viewChangerController, controller);
        final BotView botView = new BotView(viewChangerController);
        final PreMatchView preMatch = new PreMatchView(viewChangerController, controller, viewManager, matchController);
        //final MatchPanel MatchPanel = new MatchPanel(viewChangerController);

        //Adding the views to the manager
        viewManager.addView(homeView, "home");
        viewManager.addView(clubView, "club");
        viewManager.addView(botView, "bot");
        viewManager.addView(preMatch, "pre");
        //viewManager.addView(MatchPanel, "match");

        //Creating the MainFrame
        final MainFrame mainFrame = new MainFrame(viewManager);

        //Starting game
        viewChangerController.startGame();

    }
}
