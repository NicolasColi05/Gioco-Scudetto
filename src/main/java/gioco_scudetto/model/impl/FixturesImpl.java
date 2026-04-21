package gioco_scudetto.model.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.random.*;

import gioco_scudetto.model.api.Club;
import gioco_scudetto.model.api.Fixtures;
import gioco_scudetto.model.api.Pair;

public class FixturesImpl implements Fixtures{

    private ArrayList<Club> listOfClubs;
    private List<Pair<Club,Club>> fixture;
    final private Iterator<Pair<Club,Club>> fixtuIterator;
    
    /**
     * Constructor of the class, it takes a list of clubs and generates the fixture of the championship.
     * @param listOfClubs
     */
    public FixturesImpl(final ArrayList<Club> listOfClubs) {
        this.listOfClubs = listOfClubs;
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

    /**
     * Method that generates the fixture of the championship, it creates a list of pairs of clubs that will play against each other.
     */
    private void fixtureGeneration(){
        this.fixture = new ArrayList<>();
        int i;
        int j;
        for (i = 0; i < listOfClubs.size(); i++) {
            for (j = 0; i < listOfClubs.size(); j++) {
                if (listOfClubs.get(i).getName().equals(listOfClubs.get(j).getName())) {
                    fixture.add(new Pair<>(listOfClubs.get(i), listOfClubs.get(j)));
                }
            }
        }
        this.shuffleFixture();
    }

    /**
     * Method that is responsible of shuffling the fixture, in ordere to create a random fixture
     */
    private void shuffleFixture(){
        int i;
        for(i=0; i<listOfClubs.size(); i++){
            Pair <Club,Club> firstPair = fixture.get(i);
            RandomGenerator g = RandomGenerator.of("L64X128MixRandom");
            int n = g.nextInt(0, listOfClubs.size());
            Pair <Club,Club> secondPair = fixture.get(n);
            Pair <Club,Club> temp = firstPair;
            fixture.set(i,secondPair);
            fixture.set(n, temp);
        }
    }

}
