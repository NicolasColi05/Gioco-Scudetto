package giocoscudetto.model.api;

public interface Scoreboard {

    /**
     * this method returns the score of the guest team.
     * 
     * @return the guest team score of the game.
     */
    int getGuestScore();

     /**
     * this method returns the score of the home team.
     * 
     * @return the home team score of the game.
     */
    int getHomeScore();

     /**
      * this method sets the score of the home team.
     */
    void setHomeScore(int n_goals);

    /**
      * this method sets the score of the guest team.
     */
    void setGuestScore(int n_goals);

    /**
      * this method increase the score of the home team by 1.
     */
    void increaseHomeScore();

    /**
      * this method increase the score of the guest team by 1.
     */
    void increaseGuestScore();
    
}
