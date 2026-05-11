package giocoscudetto.model.impl;

import giocoscudetto.model.api.Pawn;

public class PawnImpl implements Pawn {

    private static final int MAX_POSITION = 31;

    private final int colorId;
    private int position;

    public PawnImpl(final int color) {
        this.position = 0;
        this.colorId = color;
    }

    @Override
    public void changePosition(int steps) {

        int newPosition = this.position + steps;

        if (newPosition > MAX_POSITION) {
            newPosition = MAX_POSITION;
        }


        System.out.println("Pawn moves from" + this.position + "to" + newPosition);

        this.position = newPosition;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public int getColor() {
        return this.colorId;
    }

    @Override
    public void setPosition(int position){
       this.position = position;
    }

}
