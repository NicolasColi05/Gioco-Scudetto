package gioco_scudetto.view.api;

import javax.swing.JPanel;

public interface ViewManager {

    /**
     * This method adds a view to the view manager.
     * 
     * @param panel the panel of the view to add,
     * @param name the name of the view to add.
     */
    public void addView(JPanel panel, String name);

    /**
     * This method shows the view with the given name.
     * 
      * @param name the name of the view to show.
     */
    public void showView(String name);

    /**
     * This method returns the container of the views.
     * 
      * @return the container of the views.
     */
    public JPanel getContainer();

    /**
     * method for quit the game.
     */
    public void quit();
}
