package giocoscudetto.view.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import giocoscudetto.controller.api.MatchController;
import giocoscudetto.view.api.ImageBoardLoader;

public class ImageBoardLoaderImpl implements ImageBoardLoader{

    private static final int NUMBER_OF_IMAGES = 32;
    private final MatchController matchController;
    private final ArrayList<Image> images = new ArrayList<>();

    public ImageBoardLoaderImpl(final MatchController matchController) {
        this.matchController = matchController;
        loadImages();
    }

    private void loadImages() {
        for (int i = 0; i < NUMBER_OF_IMAGES; i++) {

            try {
                BufferedImage img = ImageIO.read(new File("src/main/resources/images/backgrounds/boxes_image/"+this.matchController.getBoxImage(i)));
                this.images.add(img);
            } catch(Exception e) {
                throw new RuntimeException("Failed to load image", e);
            }
        }
    }

    @Override
    public Image getImage(final int position) {
        return images.get(position);
    }
    
    
}
