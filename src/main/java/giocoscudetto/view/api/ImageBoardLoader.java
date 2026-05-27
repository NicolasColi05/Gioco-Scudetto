package giocoscudetto.view.api;

import java.awt.Image;

/**
 * Interface for loading images for the boxes insidethe game board.
 */
public interface ImageBoardLoader {

    /**
     * Gets the image at the specified position.
     *
     * @param position the position of the image.
     * @return the image at the given position.
     */
    Image getImage(int position);

} 
