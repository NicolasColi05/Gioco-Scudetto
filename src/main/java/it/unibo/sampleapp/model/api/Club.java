package it.unibo.sampleapp.model.api;

public interface Club {
    //Getter Methods
    public String getName();
    public int getScore();
    public int getNetDiff();

    //Setter Methods
    public void setName(String name);
    public void incrementScore(int points);
    public void changeNetDiffs(int netScored, int netSufferd);

    //AGGIUNGERE I METODI PER SETTARE E GETTARE LE PEDINE
}
