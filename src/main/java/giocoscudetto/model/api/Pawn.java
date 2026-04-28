package giocoscudetto.model.api;

public interface Pawn {
    void changePosition(Club club, int steps);
    int getPosition(Club club);
    void setPosition(Club club, int position);
}
