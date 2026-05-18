package giocoscudetto.model.api;

import giocoscudetto.model.impl.MatchImpl.GameMode;

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
     * sets the number of goals of the home Club to goal 
     * @param goal
     */
    void setGoalHome(int goal);

    /**
     * sets the number of goals of the away Club to goal 
     * @param goal
     */
    void setGoalAway(int goal);

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

    /**
     * Method that return the club that is currently playing.
     * 
     * @return the club that is currently playing.
     */
    Club getCurrentPlayer();

    /**
     * Method that simulates the throw of a dice, the value of the dice is used for moving the pawn on the board.
     * 
     * @return the value of the dice throw.
     */
    int rollDice();

    /**
     * Method that simulates the throw of two dice, the value of the dice is used for the free kick event.
     * 
     * @return the value of the two dice throw.
     */
    int freeKickDice();

    /**
     * this method set the penalty mode.
     * 
     * @param mode the gamemode of the match.
     */
    void setGameMode(GameMode mode);

    /**
     * This method is for controll the state of the penalty mode.
     * 
     * @return a boolean rappresenting if is active the penalty mode.
     */
    String getGameMode();

    void setSkipTurn(Club club);

    String toString();

    Club getWinnerClub();

    Club getLoserClub();

}
