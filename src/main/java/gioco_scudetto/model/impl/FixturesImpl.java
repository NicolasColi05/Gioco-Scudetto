package gioco_scudetto.model.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.random.*;

import gioco_scudetto.model.api.Club;
import gioco_scudetto.model.api.Fixtures;
import gioco_scudetto.model.api.Pair;

public class FixturesImpl implements Fixtures{

    private int numCLubs;
    private LinkedList<Club> listOfClubs;
    private List<Pair<Club,Club>> fixture;
    private Iterator<Pair<Club,Club>> fixtuIterator;
    
    public FixturesImpl(LinkedList<Club> listOfClubs) {
        this.listOfClubs = listOfClubs;
        this.numCLubs = listOfClubs.size();
        this.fixtureGeneration();
        this.fixtuIterator = fixture.iterator();

    }
    /*utilizzo pattern builder, ciò porta a dover chiamare il costruttore due volte prima con il 
    numero di club e poi con i nomi dei club che può essere sotto forma di lista o meno

    anzi credo che basti passare una lista con i nomi dei club almeno ho sia i nomi che la lunghezza*/
    @Override
    public Pair<Club,Club> getNextMatch() {
        return this.fixtuIterator.next();
    }

    @Override
    public Pair<Club,Club> getCurrentMatch() {
        return null;
    }

    private void fixtureGeneration(){
        this.fixture = new LinkedList<>();
        int i;
        int j;
        for (i = 0; i < listOfClubs.size(); i++) {
            for (j = 0; i < listOfClubs.size(); j++) {
                if (listOfClubs.get(i).getName() != listOfClubs.get(j).getName()) {
                    fixture.add(new Pair<>(listOfClubs.get(i), listOfClubs.get(j)));
                }
            }
        }
        
    }

    private void shufflefixture(){
        int i;
        for(i=0; i<listOfClubs.size(); i++){
            Club club = listOfClubs.get(i);
        }
    }

}
