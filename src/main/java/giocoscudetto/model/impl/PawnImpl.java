package giocoscudetto.model.impl;

import java.awt.Color;

import giocoscudetto.model.api.Pawn;

public class PawnImpl implements Pawn {
    private static final int MAX_POSITION = 31;

    private int position;
    private final Color color;

    public PawnImpl(final Color color) {
        this.position = 0;
        this.color = color;
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
    public void setPosition(int position){
       this.position = position;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
