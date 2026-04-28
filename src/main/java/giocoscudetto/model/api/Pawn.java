package giocoscudetto.model.api;

public interface PawnService {
    void changePosition(Club club, int steps);
    int getPosition(Club club);
    void setPosition(Club club, int position);
}
