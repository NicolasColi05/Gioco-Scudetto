package gioco_scudetto.model.api;

public interface PawnService {
    void movePawn(Club club, int steps);
    int getPosition(Club club);
    void setPosition(Club club, int position);
}
