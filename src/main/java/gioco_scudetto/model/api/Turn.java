package gioco_scudetto.model.api;

public interface Turn {
    void chooseStartingPlayer();
    Club getCurrentPlayer();
    void switchTurn();
    int rollDice(Club club);
}
