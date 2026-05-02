package giocoscudetto.model.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class EmptyBox implements Boxes {

    private final int position;
    private final ArrayList<BufferedImage> images;

    /**
     * @param position the position of the box in the board.
     */
    public EmptyBox(int position) {
        this.position = position;
        this.images = new ArrayList<>();
        if (this.position < 9) {
            try {
                this.images.add(ImageIO.read(new File("caselle_precise/casella_12.png")));
                this.images.add(ImageIO.read(new File("caselle_precise/casella_13.png")));
                this.images.add(ImageIO.read(new File("caselle_precise/casella_23.png")));
                this.images.add(ImageIO.read(new File("caselle_precise/casella_24.png")));
                this.images.add(ImageIO.read(new File("caselle_precise/casella_25.png")));
                this.images.add(ImageIO.read(new File("caselle_precise/casella_27.png")));
                this.images.add(ImageIO.read(new File("caselle_precise/casella_29.png")));
            } catch (IOException e) {
            e.printStackTrace();
        }
        }
        
    }
    @Override
    public int getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public void event(Match match) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'event'");
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    @Override
    public Image getImage() {
        return this.images.get(new Random().nextInt(this.images.size()));
    }

}
