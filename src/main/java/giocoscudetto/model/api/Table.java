package giocoscudetto.model.api;

import java.util.List;
import giocoscudetto.model.api.Club;

/**
 * Interface that defines the correct position of each club in the table.
 */
public interface Table {
   
    /**
     * @param clubs are all the clubs that partecipate to matches
     * so will be added to the table.
     */
    void addAllClubs(List<Club> clubs);

    /**
     * This method is used to correct each team current position in the table. 
     */
    void updateClubRank();

    /**
     * @return the List with all the team in the correct order.
     */
    List<Club> showPosition();

    /**
     * Method to clear the table list, removing teams from it.
     */
    void reset();

    /**
     * @return if the table list is empty.
     */
    boolean isEmpty();

    List<Club> getClubs();
}
