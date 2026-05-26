package giocoscudetto.model.boxestest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import giocoscudetto.model.api.*;
import giocoscudetto.model.impl.ClubImpl;
import giocoscudetto.model.impl.MatchImpl;
import giocoscudetto.model.impl.boxes.ResultBox;

public class TestResultBox {
    private Club clubHome;
    private Club clubAway;
    private Match match;
    private ResultBox resultBox;

    @BeforeEach
    public void setUp() {
        clubHome = new ClubImpl("home", null);
        clubAway = new ClubImpl("away", null);
        match = new MatchImpl(clubHome, clubAway);
        resultBox = new ResultBox(5);
        if(match.getCurrentPlayer() != clubHome) {
            match.turn();
        }
    }

    @Test
    public void testEventSetsGameModeToResult() {
        assertNotEquals("RESULT", match.getGameMode());
        resultBox.event(match);
        assertEquals("RESULT", match.getGameMode());
    }

    @Test
    public void testResultBoxPosition() {
        assertEquals(5, resultBox.getPosition());
    }

    @Test
    public void testResultBoxName() {
        assertEquals("result box", resultBox.getName());
    }

    @Test
    public void testResultBoxImage() {
        assertEquals("casella_3.png", resultBox.getImage());
    }

    @Test
    public void testResultEvent() {
        final int dice1;
        final int dice2;

        resultBox.event(match);
        dice1 = match.diceEvent();
        dice2 = match.diceEvent();

        assertEquals(dice1, match.getScore().getHomeScore());
        assertEquals(dice2, match.getScore().getGuestScore());
    }
}
