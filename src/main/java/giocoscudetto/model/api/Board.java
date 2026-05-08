package giocoscudetto.model.api;

import java.awt.Image;

/**
 * This Class Board is for rappresent the board of the game with all his boxes.
 */
public interface Board {

    /**
     * This method is for obtain the type of boxes in a certain index.
     * 
     * @param index the positon in the board.
     * @return the associated Box.
     */
    Boxes getBox(int index);

    String getBoxImage(int i);
    

} 
