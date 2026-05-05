package giocoscudetto.model.api;

import java.util.List;

/**
 * Interface that defines the correct position of each club in the table.
 */
public interface Table {
   
    /**
     * This method is used to correct each team current position in the table. 
     */
    void updateClubRank();

    /**
     * @return the List with all the team in the correct order.
     */
    List<Club> showPosition();

}
