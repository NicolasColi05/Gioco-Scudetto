package giocoscudetto.model.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import giocoscudetto.model.api.Match;
import giocoscudetto.model.api.Club;
import giocoscudetto.model.api.Fixtures;
import giocoscudetto.model.api.Scoreboard;


public class FixturesImpl implements Fixtures {

    private final List<Club> listOfClubs = new LinkedList<>();
    private final List<Match> listOfMatches = new ArrayList<>();
    private Iterator<Match> listOfMatchesIterator;
    private final Map<Match,Scoreboard> fixture = new LinkedHashMap<>();
    private Match currentMatch;

    /**
     * Constructor of the class, it takes a list of clubs and generates the fixture of the championship.
     * 
     * @param listOfClubs it's the list of the clubs that will take part in the fixture
     
    public FixturesImpl(final List<Club> listOfClubs) {
        this.listOfClubs = listOfClubs;
        this.fixtureGeneration();
        this.listOfMatchesIterator = this.listOfMatches.iterator();
        this.currentMatch = null;
    }*/ 

    @Override
    public void fixtureGeneration(final List<Club> listOClubs) {
        this.listOfClubs.addAll(listOClubs);
        int i;
        int j;
        for (i = 0; i < listOfClubs.size(); i++) {
            for (j = 0; j < listOfClubs.size(); j++) {
                if (i != j) {
                    listOfMatches.add(new MatchImpl(listOfClubs.get(i), listOfClubs.get(j)));
                }
            }
        }
        java.util.Collections.shuffle(listOfMatches);
        for (Match match : listOfMatches) {
            fixture.put(match, null);
        }
        this.listOfMatchesIterator = listOfMatches.iterator();
    }

    @Override
    public Match setNextMatch() {
        if(this.listOfMatchesIterator.hasNext()){
            this.currentMatch = this.listOfMatchesIterator.next();
            return this.currentMatch;
        }
        return null;
    }

    @Override 
    public Match seeNextMatch(Match match){
        int i = listOfMatches.indexOf(match);
        if (i+1 >= listOfMatches.size()){
            return null;
        }else{
            return listOfMatches.get(++i);
        }
    }

    @Override
    public Match getCurrentMatch() {
        return this.currentMatch;
    }

    @Override
    public void setScore(Match match, Scoreboard score){
        this.fixture.replace(match,null,score);
    }

    @Override
    public void resetFixture(){
        this.fixture.clear();
        this.listOfMatches.clear();
        this.listOfClubs.clear();
    }

    @Override
    public Scoreboard getScoreboard(Match match){
        return this.fixture.get(match);
    }

    @Override
    public Set<Match> getListOfMatches() {
        return this.fixture.keySet();
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
    for (Map.Entry<Match, Scoreboard> entry : fixture.entrySet()) {
        Match match = entry.getKey();
        Scoreboard result = entry.getValue();

        // Costruzione della riga
        sb.append(String.format("%-15s vs %15s", 
                  match.getClubHome().getName(),
                  match.getClubAway().getName()));

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
