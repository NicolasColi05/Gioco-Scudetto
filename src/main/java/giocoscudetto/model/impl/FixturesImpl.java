package giocoscudetto.model.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Pair;


public class FixturesImpl implements Fixtures {

    private final List<Club> listOfClubs;
    private List<Pair<Club, Club>> fixture;
    private final Iterator<Pair<Club, Club>> fixtuIterator;

    /**
     * Constructor of the class, it takes a list of clubs and generates the fixture of the championship.
     * 
     * @param listOfClubs it's the list of the clubs that will take part in the fixture
     */
    public FixturesImpl(final List<Club> listOfClubs) {
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
            for (j = 0; j < listOfClubs.size(); j++) {
                if (listOfClubs.get(i).getName().equals(listOfClubs.get(j).getName() ) == false) {
                    fixture.add(new Pair<>(listOfClubs.get(i), listOfClubs.get(j)));
                }
            }
        }
        java.util.Collections.shuffle(fixture);
    }

    @Override
    public String toString() {
    if (this.fixture == null || this.fixture.isEmpty()) {
        return "Nessun match programmato.";
    }

    StringBuilder sb = new StringBuilder("Calendario Partite:\n");
    for (Pair<Club, Club> match : fixture) {
        sb.append(match.e1().getName())  // Assumendo che Club abbia getName()
          .append(" vs ")
          .append(match.e2().getName())
          .append("\n");
    }
    return sb.toString();
    }

}
