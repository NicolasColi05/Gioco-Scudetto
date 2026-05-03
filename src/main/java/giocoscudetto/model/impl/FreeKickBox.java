package giocoscudetto.model.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public class FreeKickBox implements Boxes{

    private BufferedImage image = null;

    public FreeKickBox() {
         try {
        this.image = ImageIO.read(new File("caselle_precise/casella_18.png"));
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
        return this.image;
    }

}
