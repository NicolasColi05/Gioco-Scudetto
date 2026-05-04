package giocoscudetto.model.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Pawn;

public class BackToStartBox implements Boxes {

    private final int position;
    private Pawn pawn;
    private final BufferedImage image;

    public BackToStartBox(final int position) {

        this.position = position;
        try {
        this.image = ImageIO.read(new File("caselle_precise/casella_31.png"));
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
        return "Back to Start";
    }

    @Override
    public void event(Match match) {
        Club club= match.turn();

        pawn.setPosition( 0);
        System.out.println(club + "go back to start");
    }

    @Override
    public Image getImage() {
        return this.image;
    }
}
