package giocoscudetto.model.api;

public interface Pawn {
    void changePosition(int steps);
    int getPosition();
    void setPosition(int position);
}
