package giocoscudetto.model.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.random.*;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Pair;

public class FixturesImpl implements Fixtures {

    final private ArrayList<Club> listOfClubs;
    private List<Pair<Club, Club>> fixture;
    private final Iterator<Pair<Club, Club>> fixtuIterator;

    /**
     * Constructor of the class, it takes a list of clubs and generates the fixture of the championship.
     * 
     * @param listOfClubs it's the list of the clubs that will take part in the fixture
     */
    public FixturesImpl(final ArrayList<Club> listOfClubs) {
        this.listOfClubs = listOfClubs;
        this.fixtureGeneration();
        this.fixtuIterator = fixture.iterator();

    }

    @Override
    public Pair<Club, Club> getNextMatch() {
        return this.fixtuIterator.next();
    }

    @Override
    public Pair<Club, Club> getCurrentMatch() {
        return null;
    }

    /**
     * Method that generates the fixture of the championship, it creates a list of pairs 
     * of the clubs that will play against each other.
     */
    private void fixtureGeneration() {
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
     * Method that is responsible of shuffling the fixture, in ordere to create a random fixture.
     */
    private void shuffleFixture() {
        int i;
        for (i = 0; i < listOfClubs.size(); i++) {
            final Pair<Club, Club> firstPair = fixture.get(i);
            final RandomGenerator g = RandomGenerator.of("L64X128MixRandom");
            final int n = g.nextInt(0, listOfClubs.size());
            final Pair<Club, Club> secondPair = fixture.get(n);
            final Pair<Club, Club> temp = firstPair;
            fixture.set(i, secondPair);
            fixture.set(n, temp);
        }
    }

}
