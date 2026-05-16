package giocoscudetto.model.api;

import java.util.List;

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
    
    Match seeNextMatch(Match match);
    
    void resetFixture();
}
