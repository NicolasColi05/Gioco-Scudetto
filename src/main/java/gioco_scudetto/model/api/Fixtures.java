package gioco_scudetto.model.api;

public interface Fixtures {
    
    void generateFixture();
    
    Pair<Club,Club> getNextMatch();
    
    Pair<Club,Club> getCurrentMatch();
}
