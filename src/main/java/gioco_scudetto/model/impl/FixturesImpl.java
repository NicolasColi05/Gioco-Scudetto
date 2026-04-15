package gioco_scudetto.model.impl;

import gioco_scudetto.model.api.Club;
import gioco_scudetto.model.api.Fixtures;
import gioco_scudetto.model.api.Pair;

public class FixturesImpl implements Fixtures{

    int numCLubs;
    
    public FixturesImpl(int numClubs){
        this.numClubs = numClubs;
    }

    @Override
    public Pair<Club,Club> getNextMatch() {

    }

    @Override
    public Pair<Club,Club> getCurrentMatch() {

    }

}
