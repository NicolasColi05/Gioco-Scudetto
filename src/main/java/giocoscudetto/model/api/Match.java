package giocoscudetto.model.api;

/**
 * Interface that represents a match between two clubs, it keeps track of the score and of the clubs that are playing
 */
public interface Match {

    enum GameMode {
        CORNER, FREE_KICK,
        RESULT, PENALTY,
        NONE
    }

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
     * Method that decrease the amount of goals scored by the home team
     */
    void removeGoalHome();

    /**
     * Method that decrease the amount of goals scored by the away team
     */
    void removeGoalAway();

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

    /**
     * Method that set the skip turn for the club that is passed as parameter, this method is used for the corner event.
     * @param club the club that has to skip the turn
     */
    void setSkipTurn(Club club);

    /**
     * Method that return a string representation of the match, it contains the name of the home and away club and the score.
     * @return a string representation of the match, it contains the name of the home and away club and the score.
     */
    String toString();

    /**
     * Method that return the club that has won the match, if there is a draw it returns null.
     * @return the club that has won the match, if there is a draw it returns null.
     */
    Club getWinnerClub();

    /**
     * Method that return the club that has lost the match, if there is a draw it returns null.
     * @return the club that has lost the match, if there is a draw it returns null.
     */
    Club getLoserClub();

    int diceEvent();

    void setKeeperPosition(int i);

    /**
     * Method that manage the event mode, it check the game mode and the value of the event 
     * dices and update the score accordingly.
     */
    void eventMode();

}
