package giocoscudetto.model.api;

import java.util.List;

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
