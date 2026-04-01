package it.unibo.sampleapp.model.api;

public interface Fixtures {
    
    Pair<Club,Club> getNextMatch();
    
    Pair<Club,Club> getCurrentMatch();
}
