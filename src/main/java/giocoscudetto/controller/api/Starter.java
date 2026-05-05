package giocoscudetto.controller.api;

import java.awt.Color;
import java.awt.Image;

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
    void setNewClub(String name);

    /**
     * this method is for get the image of a box.
     * 
     * @param i the index of the box.
     * @return the image of the box.
     */
    Image getBoxImage(int i);

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
     * when called this method returns the fixture
     * 
     * @return the fixture
     */
    Fixtures getFixture();

    /**
     * when called this method returns the fixture
     * 
     * @return the table
     */
    Table getTable();
}
