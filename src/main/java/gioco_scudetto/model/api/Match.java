package gioco_scudetto.model.api;

public interface Match {

    Club getClubHome();

    Club getClubAway();

    Scoreboard getScore();

    int getMatchStatus();
}
