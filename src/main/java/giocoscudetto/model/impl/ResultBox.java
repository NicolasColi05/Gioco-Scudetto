package giocoscudetto.model.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class ResultBox implements Boxes{

    private final BufferedImage image;
    private final int position;
    private final String description = "Box Event: Result. If you land on this box, you have to throw 2 dice and the numbers you get makes the new score";

    public ResultBox(final int position) {
        this.position = position;
        try {
        this.image = ImageIO.read(new File("caselle_precise/casella_3.png"));
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
        return this.image;
    }
    @Override
    public String getDescription() {
        return this.description;
    }

}
