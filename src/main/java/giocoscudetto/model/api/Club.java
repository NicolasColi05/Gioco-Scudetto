package giocoscudetto.model.api;

import java.awt.Color;

public interface Club {
    
    String getName();
    int getPoints();
    int getNetDiff();
    Pawn getPawn();
    Color getColor();

    //Setter Methods
    void setName(String name);
    void incrementPoints(int points);
    void changeNetDiffs(int goalScored, int goalConceded);
    void changePawn(Pawn pawnSelected);
    void setColor(Color color);


}
  