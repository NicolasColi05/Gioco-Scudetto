package it.unibo.sampleapp.model.api;

public interface Club {
    //Getter Methods
    String getName();
    int getPoints();
    int getNetDiff();

    //Setter Methods
    void setName(String name);
    void incrementPoints(int points);
    void changeNetDiffs(int goalScored, int goalConceded);

    //AGGIUNGERE I METODI PER SETTARE E GETTARE LE PEDINE
}
  