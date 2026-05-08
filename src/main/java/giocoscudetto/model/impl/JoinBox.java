package giocoscudetto.model.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;



public class JoinBox implements Boxes {

    private final int position;
    private final BufferedImage image;
    private final String description = "Box Event: Join. If you land on this box, the opponent must have to reach your box.";

    public JoinBox(int position) {
       this.position = position;
        try {
        this.image = ImageIO.read(new File("caselle_precise/casella_2.png"));
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
        return "Join Box";
    }

    @Override
    public void event (Match match) {
        Club current= match.turn();
        Club opponent;
    
        if (current == match.getClubHome()){
        opponent = match.getClubAway();
        } else {
            opponent = match.getClubHome();
        }
            

        System.out.println(current + "entered Join Box");

        int currentPosition = current.getPawn().getPosition();
        opponent.getPawn().setPosition(currentPosition);

        System.out.println(opponent + "joined current player at position" + currentPosition);
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
