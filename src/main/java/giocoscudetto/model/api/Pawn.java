package giocoscudetto.model.api;


/**
 * Represents a pawn used by a club on the game board.
 */
public interface Pawn {


    /**
     * Moves the pawn by the number of steps.
     * 
     * @param steps number of position to move 
     */
    void changePosition(int steps);

    /**
     * Returns the current position of the pawn on the board.
     * 
     * @return the current position of the pawn.
     */
    int getPosition();

    /**
     * Sets the position of the pawn on the board.
     * 
     * @param position the position to set for the pawn.
     */
    void setPosition(int position);

    /**
     * Returns the color of the pawn.
     * 
     * @return a string representing the color of the pawn.
     */
    int getPawnRGB();
}
