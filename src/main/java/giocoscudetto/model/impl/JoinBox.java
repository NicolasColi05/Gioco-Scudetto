package giocoscudetto.model.impl;

import java.awt.Image;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;



public class JoinBox implements Boxes {
    private final int position;
    public JoinBox(int position) {
        this.position = position;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    }
}
