package giocoscudetto.model.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Pair;
import giocoscudetto.model.api.Scoreboard;


public class FixturesImpl implements Fixtures {

    private final List<Club> listOfClubs;
    private List<Pair<Club, Club>> listOfMatches;
    private final Iterator<Pair<Club, Club>> listOfMatchesIterator;
    private Map<Pair<Club, Club>,Scoreboard> fixture;
    private Pair<Club, Club> currentMatch;

    /**
     * Constructor of the class, it takes a list of clubs and generates the fixture of the championship.
     * 
     * @param listOfClubs it's the list of the clubs that will take part in the fixture
     */
    public FixturesImpl(final List<Club> listOfClubs) {
        this.listOfClubs = listOfClubs;
        this.fixtureGeneration();
        this.listOfMatchesIterator = this.listOfMatches.iterator();
        this.currentMatch = null;
    }

    @Override
    public Pair<Club, Club> getNextMatch() {
        if(this.listOfMatchesIterator.hasNext()){
            this.currentMatch = this.listOfMatchesIterator.next();
            return this.currentMatch;
        }
        return null;
    }

    @Override
    public Pair<Club, Club> getCurrentMatch() {
        return this.currentMatch;
    }

    /**
     * Method that generates the fixture of the championship, it creates a list of pairs 
     * of the clubs that will play against each other.
     */
    private void fixtureGeneration() {
        this.fixture = new LinkedHashMap<>();
        this.listOfMatches = new ArrayList<>();
        int i;
        int j;
        for (i = 0; i < listOfClubs.size(); i++) {
            for (j = 0; j < listOfClubs.size(); j++) {
                if (!(listOfClubs.get(i).getName().equals(listOfClubs.get(j).getName()))) {
                    listOfMatches.add(new Pair<>(listOfClubs.get(i), listOfClubs.get(j)));
                }
            }
        }
        java.util.Collections.shuffle(listOfMatches);
        for (Pair<Club,Club> match : listOfMatches) {
            fixture.put(match, null);
        }
    }

    @Override
    public String toString() {
    /*if (this.listOfMatches == null || this.listOfMatches.isEmpty()) {
        return "Nessun match programmato.";
    }

    final StringBuilder sb = new StringBuilder("Calendario Partite:\n");
    for (final Pair<Club, Club> match : listOfMatches) {
        sb.append(match.e1().getName())  // Assumendo che Club abbia getName()
          .append(" vs ")
          .append(match.e2().getName())
          .append("\n");
    }
    
    return sb.toString();*/
    StringBuilder sb = new StringBuilder();
    
    sb.append("--- CALENDARIO E RISULTATI ---\n");
    
    if (fixture.isEmpty()) {
        sb.append("Nessuna partita in programma.");
        return sb.toString();
    }

    // Iteriamo sull'entry set per avere accesso a chiave e valore
    for (Map.Entry<Pair<Club, Club>, Scoreboard> entry : fixture.entrySet()) {
        Pair<Club, Club> match = entry.getKey();
        Scoreboard result = entry.getValue();

        // Costruzione della riga
        sb.append(String.format("%-15s vs %15s", 
                  match.e1().getName(),
                  match.e2().getName()));

        sb.append("  |  Risultato: ");
        
        // Gestione del valore nullo o del risultato non ancora presente
        if (result == null) {
            sb.append("DA GIOCARE");
        } else {
            sb.append(result.getHomeScore())
              .append(" - ")
              .append(result.getGuestScore());
        }
        
        sb.append("\n");
    }
    
    sb.append("-------------------------------");
    return sb.toString();

    }

}
