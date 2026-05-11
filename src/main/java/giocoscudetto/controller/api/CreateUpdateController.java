package giocoscudetto.controller.api;

import java.util.List;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Table;


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
     * @return the list of clubs.
     */
    List<Club> getClubs();

    /**
     * @return the list of clubs.
     */
    Table getTable();


    /**
     * @return the list of clubs.
     */
    Fixtures getFixture();


    /**
     * Method to update each club position in the table
     */
    void updateClubActualRank();

    /**
     * @return the list containg the club in the right position
     */
    List<Club> getClubActualRank();

}
