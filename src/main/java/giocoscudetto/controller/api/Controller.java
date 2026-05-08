package giocoscudetto.controller.api;

import java.util.List;


/**
 * Controller Interface to cominicate between model and view
 */
public interface Controller {
    
    /**
     * Method to create the clubs that will play the incoming match.
     * 
     * @param clubs, the clubs that will play the match.
     */
    void createClubs(List<String> clubsName);

}
