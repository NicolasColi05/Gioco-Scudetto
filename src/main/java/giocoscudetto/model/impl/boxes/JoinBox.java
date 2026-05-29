package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Boxes;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Match;


public class JoinBox implements Boxes {

    private final int position;
    private static final String IMAGE = "casella_2.png";
    private static final String DESCRIPTION = "Box Event: Join. If you land on this box, the opponent has to reach your box.";

    public JoinBox(int position) {
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosition() {
        return position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Join Box";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void event (Match match) {
        Club current= match.getCurrentPlayer();
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
        match.turn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
        return JoinBox.IMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return JoinBox.DESCRIPTION;
    }
}
