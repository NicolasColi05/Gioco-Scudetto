package giocoscudetto.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import giocoscudetto.model.impl.ScoreboardImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.ScoreboardImpl}.
 */
public class TestScoreBoard {

    private ScoreboardImpl scoreboard;

    @BeforeEach
    public void setUp() {
        scoreboard = new ScoreboardImpl();
    }

    @Test
    public void testGetHomeScore() {
        scoreboard.setHomeScore(3);
        assertEquals(3, scoreboard.getHomeScore());
    }

    @Test
    public void testGetGuestScore() {
        scoreboard.setGuestScore(2);
        assertEquals(2, scoreboard.getGuestScore());
    }

    @Test
    public void testSetHomeScore() {
        scoreboard.setHomeScore(5);
        assertEquals(5, scoreboard.getHomeScore());
    }

    @Test
    public void testSetGuestScore() {
        scoreboard.setGuestScore(4);
        assertEquals(4, scoreboard.getGuestScore());
    }

    @Test
    public void testIncreaseHomeScore() {
        scoreboard.increaseHomeScore();
        assertEquals(1, scoreboard.getHomeScore());
        scoreboard.increaseHomeScore();
        assertEquals(2, scoreboard.getHomeScore());
    }

    @Test
    public void testIncreaseGuestScore() {
        scoreboard.increaseGuestScore();
        assertEquals(1, scoreboard.getGuestScore());
        scoreboard.increaseGuestScore();
        assertEquals(2, scoreboard.getGuestScore());
    }

    @Test
    public void testDecreaseHomeScore() {
        scoreboard.setHomeScore(3);
        scoreboard.decreaseHomeScore();
        assertEquals(2, scoreboard.getHomeScore());
        scoreboard.setHomeScore(1);
        scoreboard.decreaseHomeScore();
        assertEquals(0, scoreboard.getHomeScore());
        scoreboard.decreaseHomeScore();
        assertEquals(0, scoreboard.getHomeScore(), "Home score should not go below 0");
    }
    
    @Test
    public void testDecreaseGuestScore() {
        scoreboard.setGuestScore(3);
        scoreboard.decreaseGuestScore();
        assertEquals(2, scoreboard.getGuestScore());
        scoreboard.setGuestScore(1);
        scoreboard.decreaseGuestScore();
        assertEquals(0, scoreboard.getGuestScore());
        scoreboard.decreaseGuestScore();
        assertEquals(0, scoreboard.getGuestScore(), "Guest score should not go below 0");
    }

    @Test
    public void testToString() {
        scoreboard.setHomeScore(2);
        scoreboard.setGuestScore(1);
        assertEquals("2 - 1", scoreboard.toString());
    }

}
