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
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Match setNextMatch() {
        if(this.listOfMatchesIterator.hasNext()){
            this.currentMatch = this.listOfMatchesIterator.next();
            return this.currentMatch;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public Match seeNextMatch(Match match){
        int i = listOfMatches.indexOf(match);
        if (i+1 >= listOfMatches.size()){
            return null;
        }else{
            return listOfMatches.get(++i);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Match getCurrentMatch() {
        return this.currentMatch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScore(Match match, Scoreboard score){
        this.fixture.replace(match,null,score);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetFixture(){
        this.fixture.clear();
        this.listOfMatches.clear();
        this.listOfClubs.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scoreboard getScoreboard(Match match){
        return this.fixture.get(match);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Match> getListOfMatches() {
        return this.fixture.keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty(){
        return this.fixture.isEmpty();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
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
