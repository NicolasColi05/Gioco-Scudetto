package gioco_scudetto.model.api;

/**
 * Interface that represents a match between two clubs, it keeps track of the score and of the clubs that are playing
 */
public interface Match {

    /**
     * Method that returns the club that has the turn to play
     * 
     * @return the club that has the turn to play
     */
    Club turn();

    /**
     * Method that increase the amount of goals scored by the home team
     */
    void goalHome();

    /**
     * Method that increase the amount of goals scored by the away team
     */
    void goalAway();

    /**
     * Method that return the home club
     * 
     * @return the home club
     */
    Club getClubHome();

    /**
     * Method that return the away club
     * 
     * @return the away club
     */
    Club getClubAway();

    /**
     * Method that return the score of the match
     * 
     * @return the score of the match
     */
    Scoreboard getScore();

}
