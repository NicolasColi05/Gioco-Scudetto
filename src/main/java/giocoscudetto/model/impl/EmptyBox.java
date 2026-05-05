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
    public EmptyBox(final int position) {
        this.position = position;
        this.images = new ArrayList<>();
        try {
            switch (position) {
                case(1): this.images.add(ImageIO.read(new File("caselle_precise/casella_12.png")));
                         break;
                case(3): this.images.add(ImageIO.read(new File("caselle_precise/casella_13.png")));
                        break;
                case(5):this.images.add(ImageIO.read(new File("caselle_precise/casella_23.png")));
                break;
                case(7):this.images.add(ImageIO.read(new File("caselle_precise/casella_24.png")));
                break;
                case(9):this.images.add(ImageIO.read(new File("caselle_precise/casella_25.png")));
                break;
                case(11):this.images.add(ImageIO.read(new File("caselle_precise/casella_27.png")));
                break;
                case(13):this.images.add(ImageIO.read(new File("caselle_precise/casella_29.png")));
                break;
                case(18):this.images.add(ImageIO.read(new File("caselle_precise/casella_4.png")));
                break;
                case(20):this.images.add(ImageIO.read(new File("caselle_precise/casella_6.png")));
                break;
                case(22):this.images.add(ImageIO.read(new File("caselle_precise/casella_8.png")));
                break;
                case(25):this.images.add(ImageIO.read(new File("caselle_precise/casella_17.png")));
                break;
                case(27):this.images.add(ImageIO.read(new File("caselle_precise/casella_20.png")));
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load image", e);  
        }
        
    }
    @Override
    public int getPosition() {
        return this.position;
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
