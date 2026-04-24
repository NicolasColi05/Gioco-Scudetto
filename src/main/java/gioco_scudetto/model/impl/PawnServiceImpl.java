package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.PawnService;
import gioco_scudetto.model.api.Club;
import gioco_scudetto.model.api.Pawn;

public class PawnServiceImpl implements PawnService {
    private static final int MAX_POSITION = 31;

    @Override
    public void changePosition(Club club, int steps) {
        Pawn pawn = club.getPawn();
        int currentPosition = pawn.getPosition();
        int newPosition = currentPosition + steps;
        if (newPosition > MAX_POSITION) {
            newPosition = MAX_POSITION;
        }

        pawn.setPosition(newPosition);

        System.out.println(club + "moves from" + currentPosition + "to" + newPosition);
    }

    @Override
    public int getPosition(Club club) {
        return club.getPawn().getPosition();
    }

    @Override
    public void setPosition(Club club, int position){
        club.getPawn().setPosition(position);
    }
}
