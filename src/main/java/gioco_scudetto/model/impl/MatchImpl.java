package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.Club;
import gioco_scudetto.model.api.Match;
import gioco_scudetto.model.api.Scoreboard;

public class MatchImpl implements Match{

    private Club clubHome;
    private Club clubAway;
    private Scoreboard score;
    enum status {IN_CORSO, FINE_PRIMO_TEMPO, FINE_PARTITA};

    public MatchImpl(){
        this.score = new ScoreboardImpl();
    }
    
    @Override
    public Club turn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'turn'");
    }

    @Override
    public void goalHome() {
        
    }

    @Override
    public void goalAway() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'goalAway'");
    }

    @Override
    public Club getClubHome() {
        return clubHome;
    }

    @Override
    public Club getClubAway() {
        return clubAway;
    }

    @Override
    public Scoreboard getScore() {
        return score;
    }

    @Override
    public int getMatchStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMatchStatus'");
    }
    
}
