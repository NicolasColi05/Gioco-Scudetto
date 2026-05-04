package giocoscudetto.model.api;

/**
 * Interface to memorize the clubs information.
 */
public interface Club {
    
    /**
     * @return the name of the club.
     */
    String getName();

    /**
     * @return the point of the club.
     */
    int getPoints();

    /**
     * @return the difference between net scored and taken during the matches.
     */
    int getNetDiff();

    /**
     * @return the pawn selected by the club.
     */
    Pawn getPawn();

    /**
     * Set the name of the club.
     * 
     * @param name is the name selected by the team.
     */
    void setName(String name);

    /**
     * Increments the points of the club.
     * 
     * @param points to be summed to the actual total.
     */
    void incrementPoints(int points);

    /**
     * Subtracting goalScored and goalConceded and then setting the new netDiff value.
     * 
     * @param goalScored in the match that just ended.
     * @param goalConceded in the match that just ended.
     */
    void changeNetDiffs(int goalScored, int goalConceded);

    /**
     * Setting the pawn selected by the club.
     * 
     * @param pawnSelected
     */
    void setPawn(Pawn pawnSelected);

}
  