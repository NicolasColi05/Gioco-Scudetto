package gioco_scudetto.model.api;

public interface Match {

    Club turn();
    
    void goalHome();

    void goalAway();

    Club getClubHome();

    Club getClubAway();

    Scoreboard getScore();

    int getMatchStatus(); //enum StatoPartita { IN_CORSO, FINE_PRIMO_TEMPO, FINE_PARTITA }
}
