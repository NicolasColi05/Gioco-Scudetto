package giocoscudetto.controller.api;

import java.awt.Color;
import java.util.ArrayList;

import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Table;

public interface Starter {
    
    /**
     * this method is for starting the game and show the home view.
     */
    void startGame();

    /**
     * this is a method for change the panel in the frame.
     * 
     * @param namePanel the name of the panel.
     */
    void changeView(String namePanel);

    /**
     * this is a method to close the game.
     */
    void closeGame();

    /**
     * this is a method to check the box and execute the event of the box.
     */
    void checkBox();

     /**
     * this method is used to set the number of clubs that
     * will participate in the championship
     */
    void setNumberOfClubs(int n);
    
    /**
     * this method is used to create a new club
     * 
     */
    void setClubs(ArrayList<String> names, ArrayList<String> pawns);

    /**
     * this method is for get the image of a box.
     * 
     * @param i the index of the box.
     * @return the image of the box.
     */
    String getBoxImage(int i);

    /**
     *  this method is for get the score of the game.
     * 
     * @return a string that contains the score of the game.
     */
    String getScore();
 
    /**
     * this method is for get the color of the home team.
     * 
     * @return the Color of the home team.
     */
    Color getHomeTeamColor();

    /**
     * this method is for get the position of the home team pawn.
     * 
     * @return the position of the pawn of the home team.
     */
    int getHomePosition();

    /**
     * this method set the goalkeeper postion in the goalnetimpl.
     * 
     * @param i the position occupied by the keeper.
     */
    void setKeeperPosition(int i);

    /**
     * this method is for get the color of the guest team.
     * 
     * @return the Color of the guest team.
     */
    Color getGuestTeamColor();

    /**
     * this method is for get the position of the guest team pawn.
     * 
     * @return the position of the pawn of the guest team.
     */
    int getGuestPosition();

    /**
     * this method is for kick the penalty and check if it's a goal or not.
     * 
     * @return true if it's a goal, false otherwise.
     */
    boolean kickPenalty();

    /**
     * this method is for get the current player,who has to move.
     * 
     * @return a string that contains the name of the current player.
     */
    String getCurrentPlayer();

    /**
     * this method is for move the pawn of the current player.
     */
    void move();

     /**
      * this method is for get the fixture of the championship. 
      * 
      * @return the fixture of the championship.
      */
    Fixtures getFixture();

    /**
     * when called this method returns the fixture
     * 
     * @return the table
     */
    Table getTable();

    /**
     * this method is for get the description of the current box.
      *
     * @return a string that contains the description of the current box.
     */
    String getDescription();
}
