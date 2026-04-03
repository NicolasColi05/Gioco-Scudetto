package gioco_scudetto.model.api;

public interface Fixtures {
    
    Pair<Club,Club> getNextMatch();
    
    Pair<Club,Club> getCurrentMatch();
}
