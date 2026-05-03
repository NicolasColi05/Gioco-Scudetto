package giocoscudetto.model.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class ResultBox implements Boxes{

    private final ArrayList<BufferedImage> images;

    public ResultBox() {
        this.images = new ArrayList<>();
        try {
            this.images.add(ImageIO.read(new File("caselle_precise/casella_28.png")));
            
        } catch (IOException e) {
            e.printStackTrace();
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
        return this.images.get(0);
    }

}
