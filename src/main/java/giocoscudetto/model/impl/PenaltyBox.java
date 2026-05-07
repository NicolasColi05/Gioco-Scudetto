package giocoscudetto.model.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Match;

public final class PenaltyBox implements Boxes {
    
    private final int position;
    private final String name;
    private final BufferedImage image;
    private final String description = "Box Event: Penalty. If you land on this box, The opponent must decide the position of the goalkeeper,"
                                    + " once finished you kick the penalty which consists of throwing a dice [1-6] if you roll a number not selected by the opponent you score a goal.";

    public PenaltyBox(final int position) {
        this.position = position;
        this.name = "Penalty Box";
        try {
        this.image = ImageIO.read(new File("caselle_precise/casella_16.png"));
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
    public void event(final Match match) {
        
    }

    @Override
    public String getName() {
        return this.name;
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
