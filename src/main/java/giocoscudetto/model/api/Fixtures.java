package giocoscudetto.model.api;

import java.util.List;
import java.util.Set;

/**
 * Interface that represents the fixture of the championship, it is responsible for generating the matches
 * and for providing the next match to be played.
 */
public interface Fixtures {

    /**
     * Method that generates the fixture of the championship, it creates a list of pairs 
     * of the clubs that will play against each other, and from that it creates a map to 
     * also store the results of the matches
     */
    public void fixtureGeneration(final List<Club> listOClubs);
    /**
     * Method that returns the next match to be played, it returns a pair of clubs that will play against each other.
     * 
     * @return the next match to be played
     */
    Match setNextMatch();

    /**
     * Method that returns the current match being played, it returns a pair of clubs that are playing against each other.
     * 
     * @return the current match being played
     */
    Match getCurrentMatch();

    /**
     * Method that returns a string representing the fixture, with all the matches
     * @return a string that represents the fixture
     */
    String toString();

    /**
     * Method that adds the score to the related match
     * @param match refers to the match where we need to update the score
     * @param score is the updated score for the match
     */
    void setScore(Match match, Scoreboard score);
    
    /**
     * Method that when given a match returns the next match to be played, without changing the value of the iterator
     * @param match the match for which you want to see the next match
     * @return the match after the match given
     */
    Match seeNextMatch(Match match);
    
    /**
     * Method that resets the fixture, clearing all matches and scores
     */
    void resetFixture();

    /**
     * Method that returns the scoreboard of a match, it returns null if the match has not been played yet
     * @param match the match for which you want to see the scoreboard
     * @return the scoreboard of the match, or null if the match has not been played yet
     */
    Scoreboard getScoreboard(Match match);

    /**
     * Method that returns the list of matches of the fixture
     * @return the list of matches of the fixture
     */
    Set<Match> getListOfMatches();
    
    /**
     * Method that returns true if the fixture is empty, false otherwise
     * @return true if the fixture is empty, false otherwise
     */
    boolean isEmpty();
}
