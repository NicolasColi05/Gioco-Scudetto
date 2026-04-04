package gioco_scudetto.model.api;

public interface Club {
    //Getter Methods
    String getName();
    int getPoints();
    int getNetDiff();
    Pawn getPawn();

    //Setter Methods
    void setName(String name);
    void incrementPoints(int points);
    void changeNetDiffs(int goalScored, int goalConceded);
    void changePawn(Pawn pawnSelected);

    //AGGIUNGERE I METODI PER SETTARE E GETTARE LE PEDINE
}
  