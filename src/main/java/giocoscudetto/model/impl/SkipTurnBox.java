package giocoscudetto.model.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;

public class SkipTurnBox implements Boxes {

    private final BufferedImage image;
    private final int position;
    private final String description = "Box Event: Skip Turn. If you land on this box, you lose your next turn.";

    public SkipTurnBox(final int position) {
        this.position = position;
        try {
        this.image = ImageIO.read(new File("caselle_precise/casella_7.png"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load image", e);
        }
        
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override 
    public String getName() {
        return "Skip Turn";
    }

    @Override
    public void event(Match match) {
        Club club=match.turn();

        System.out.println(club + "skip the next turn");
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
