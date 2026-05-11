package giocoscudetto.controller.api;

import java.util.List;


/**
 * Controller Interface to cominicate between model and view in pre match
 * configuation, table and fixture creation and update.
 */
public interface CreateUpdateController {
    
    /**
     * Method to create the clubs that will play the incoming match, the table
     * to memorize their position and the fixture.
     * 
     * @param clubs, the clubs that will play the match.
     */
    void createClubs(List<String> clubsName);

}
