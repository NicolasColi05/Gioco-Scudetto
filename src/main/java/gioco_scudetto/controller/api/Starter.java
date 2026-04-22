package gioco_scudetto.controller.api;

public interface Starter {
    
    /**
     * this method is for starting the game and show the home view.
     */
    void startGame();

    /**
     * this is a method for change the panel in the frame.
     * 
     * @param namePanel the name of the panel.
     */
    void changeView(String namePanel);

}
