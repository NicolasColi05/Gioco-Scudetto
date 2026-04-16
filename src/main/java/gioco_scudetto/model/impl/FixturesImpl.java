package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.Club;
import gioco_scudetto.model.api.Fixtures;
import gioco_scudetto.model.api.Pair;

public class FixturesImpl implements Fixtures{

    private int numCLubs;
    
    public FixturesImpl(int numClubs){
        this.numCLubs = numClubs;
    }

    @Override
    public Pair<Club,Club> getNextMatch() {
        return null;
    }

    @Override
    public Pair<Club,Club> getCurrentMatch() {
        return null;
    }

}
