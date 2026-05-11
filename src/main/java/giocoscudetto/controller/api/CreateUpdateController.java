package giocoscudetto.controller.api;

import java.util.List;

import giocoscudetto.model.api.Club;


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

    /**
     * Method to update each club position in the table
     */
    void updateClubActualRank();

    /**
     * @return the list containg the club in the right position
     */
    List<Club> getClubActualRank();

}
