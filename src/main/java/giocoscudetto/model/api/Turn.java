package giocoscudetto.model.api;

/**
 * interfaces that manages player turns during a match.
 */
public interface Turn {

    /** 
     * Chooses the starting player.
     */
    void chooseStartingPlayer();

    /**
     * Returns the current player.
     * 
     * @return the player whose turn it is 
     */
    Club getCurrentPlayer();

    /**
     * Switches the turn to the oter player.
     */
    void switchTurn();
}
