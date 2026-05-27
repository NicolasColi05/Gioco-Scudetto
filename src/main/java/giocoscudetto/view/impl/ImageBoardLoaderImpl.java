package giocoscudetto.view.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import giocoscudetto.controller.api.Starter;
import giocoscudetto.view.api.ImageBoardLoader;

/**
 * This class implements the ImageBoardLoader interface, 
 * it loads the images for the board and provides 
 * a method to get the image at a specific position.
 */
public class ImageBoardLoaderImpl implements ImageBoardLoader {

    private static final int NUMBER_OF_IMAGES = 32;
    private final Starter controller;
    private final List<Image> images = new ArrayList<>();

    /**
     * Constructor of the ImageBoardLoaderImpl class.
     * 
     * @param controller the game controller, used to get the image names for the boxes.
     * @throws IOException if an error occurs while loading the images. 
     */
    public ImageBoardLoaderImpl(final Starter controller) throws IOException {
        this.controller = controller;
        loadImages();
    }

    private void loadImages() throws IOException {
        for (int i = 0; i < NUMBER_OF_IMAGES; i++) {

            try {
                final BufferedImage img = ImageIO.read(new File("src/main/resources/images/backgrounds/boxes_image/"
                                 + this.controller.getBoxImage(i)));
                this.images.add(img);
            } catch (final IOException e) {
                throw new IOException("Failed to load image", e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage(final int position) {
        return images.get(position);
    }
}
