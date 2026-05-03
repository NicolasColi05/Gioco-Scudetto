package giocoscudetto.model.api;

/**
 * Interface that represents the fixture of the championship, it is responsible for generating the matches
 * and for providing the next match to be played.
 */
public interface Fixtures {

    /**
     * Method that returns the next match to be played, it returns a pair of clubs that will play against each other.
     * 
     * @return the next match to be played
     */
    Pair<Club, Club> getNextMatch();

    /**
     * Method that returns the current match being played, it returns a pair of clubs that are playing against each other.
     * 
     * @return the current match being played
     */
    Pair<Club, Club> getCurrentMatch();

    /**
     * Method that returns a string representing the fixture, with all the matches
     * @return a string that represents the fixture
     */
    String toString();
}
