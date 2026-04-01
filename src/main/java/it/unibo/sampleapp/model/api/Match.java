package it.unibo.sampleapp.model.api;

public interface Match {

    Club getClubHome();

    Club getClubAway();

    Scoreboard getScore();

    int getMatchStatus();
}
