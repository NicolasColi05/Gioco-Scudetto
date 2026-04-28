package giocoscudetto.model.impl;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Pawn;

public class PawnImpl implements Pawn {
    private static final int MAX_POSITION = 31;

    public PawnImpl(){
        
        
    }
    @Override
    public void changePosition(Club club, int steps) {

        int currentPosition = club.getPawn().getPosition(club);
        int newPosition = currentPosition + steps;
        if (newPosition > MAX_POSITION) {
            newPosition = MAX_POSITION;
        }

        club.getPawn().setPosition(club, newPosition);

        System.out.println(club + "moves from" + currentPosition + "to" + newPosition);
    }

    @Override
    public int getPosition(Club club) {
        return club.getPawn().getPosition(club);
    }

    @Override
    public void setPosition(Club club, int position){
        club.getPawn().setPosition(club, position);
    }
}
