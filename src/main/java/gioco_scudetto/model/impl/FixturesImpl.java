package gioco_scudetto.model.impl;

import java.util.ArrayList;
import java.util.List;

import gioco_scudetto.model.api.Club;
import gioco_scudetto.model.api.Fixtures;
import gioco_scudetto.model.api.Pair;

public class FixturesImpl implements Fixtures{

    private int numCLubs;
    private List<Club> listOfClubs;
    private List<Pair<Club,Club>> fixture;
    
    public FixturesImpl(List<Club> listOfClubs) {
        this.listOfClubs = listOfClubs;
        this.numCLubs = listOfClubs.size();
        this.fixtureGeneration();
    }
    /*utilizzo pattern builder, ciò porta a dover chiamare il costruttore due volte prima con il 
    numero di club e poi con i nomi dei club che può essere sotto forma di lista o meno

    anzi credo che basti passare una lista con i nomi dei club almeno ho sia i nomi che la lunghezza*/
    @Override
    public Pair<Club,Club> getNextMatch() {
        return null;
    }

    @Override
    public Pair<Club,Club> getCurrentMatch() {
        return null;
    }

    private void fixtureGeneration(){
        this.fixture = new ArrayList<>();
        return;
    }

}
