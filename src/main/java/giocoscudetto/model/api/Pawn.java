package giocoscudetto.model.api;

import java.awt.Color;

public interface Pawn {

    void changePosition(int steps);

    int getPosition();

    void setPosition(int position);

    Color getColor();

}
