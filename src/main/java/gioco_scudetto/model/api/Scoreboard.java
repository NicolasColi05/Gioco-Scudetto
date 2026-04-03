package gioco_scudetto.model.api;

public interface Scoreboard {

    int getGuestScore();

    int getHomeScore();

    void setHomeScore(int n_goals);

    void setGuestScore(int n_goals);

    void increaseHomeScore();

    void increaseGuestScore();
    
}
