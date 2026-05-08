package giocoscudetto.model.api;

public interface Turn {
    void chooseStartingPlayer();
    Club getCurrentPlayer();
    void switchTurn();
}
